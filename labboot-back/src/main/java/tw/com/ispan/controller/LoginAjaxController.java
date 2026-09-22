package tw.com.ispan.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tw.com.ispan.domain.CustomerBean;
import tw.com.ispan.jwt.JsonWebTokenUtility;
import tw.com.ispan.service.CustomerService;
import tw.com.ispan.utils.DatetimeConverter;


@RestController
@RequiredArgsConstructor
public class LoginAjaxController {
	private final CustomerService customerService;
	private final JsonWebTokenUtility jsonWebTokenUtility;

	@GetMapping("/ajax/secure/check/{username}")
	public String checkUsername(@PathVariable String username) {
		if(username!=null && username.length()!=0) {
			if(customerService.existByCustid(username)) { 
				return "帳號已存在";
			}
		}
		return "帳號不存在";
	}

	@PostMapping("/ajax/secure/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
//接收資料
		JSONObject obj = new JSONObject(json);
		String username = obj.isNull("username") ? null : obj.getString("username");
		String password = obj.isNull("password") ? null : obj.getString("password");

//驗證資料
		if(username==null || username.length()==0 || password==null || password.length()==0) {
			responseJson.put("success", false);
			responseJson.put("message", "請輸入帳號與密碼");
			return responseJson.toString();
		}

//呼叫model
		CustomerBean bean = customerService.login(username, password);

//回傳執行結果
		if(bean==null) {
			responseJson.put("success", false);
			responseJson.put("message", "登入失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "登入成功");
			// jwt begin
			JSONObject user = new JSONObject()
				.put("custid", bean.getCustid())
				.put("birth", DatetimeConverter.toString(bean.getBirth(), "yyyy-MM-dd"))
				.put("email", bean.getEmail());

			String token = jsonWebTokenUtility.createToken(user.toString());
			responseJson.put("token", token);
			responseJson.put("email", bean.getEmail());
			// jwt end
		}
		return responseJson.toString();
	}
}
