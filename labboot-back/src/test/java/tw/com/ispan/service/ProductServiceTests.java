package tw.com.ispan.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.ProductBean;

@SpringBootTest
public class ProductServiceTests {
	@Autowired
	private ProductService productService;
	@Test
	public void testFind() {
		JSONObject obj = new JSONObject()
//				.put("id", 5)
				.put("start", 0)
				.put("rows", 3)
				.put("order", "id")
				.put("dir", false);
		
		obj = obj.put("start", 0);
		
		
		Long count = productService.count(obj.toString());
		System.out.println("count="+count);
		
		List<ProductBean> find = productService.find(obj.toString());
		if(find!=null) {
			for(ProductBean product : find) {
				System.out.println("product="+product);
			}
		}
		
	}
}
