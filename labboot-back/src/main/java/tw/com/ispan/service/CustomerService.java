package tw.com.ispan.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import tw.com.ispan.domain.CustomerBean;
import tw.com.ispan.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public boolean existByCustid(String custid) {
		return customerRepository.existsById(custid);
	}
	
	public CustomerBean login(String username, String password) {
		if(username!=null && username.length()!=0 && password!=null && password.length()!=0) {
			CustomerBean login = customerRepository.findById(username).orElse(null);
			if(login!=null) {
				byte[] temp = password.getBytes();	//使用者輸入
				byte[] pass = login.getPassword();	//資料庫抓出
				if(Arrays.equals(temp, pass)) {
					return login;
				}
			}
		}
		return null;
	}
	public boolean changePassword(String username, String oldPass, String newPass) {
		if(username!=null && username.length()!=0 && oldPass!=null && oldPass.length()!=0 && newPass!=null && newPass.length()!=0) {
			CustomerBean login = this.login(username, oldPass);
			if(login!=null) {
				login.setPassword(newPass.getBytes());
				CustomerBean update = customerRepository.save(login);
				if(update!=null) {
					return true;
				}
			}
		}
		return false;
	}
}
