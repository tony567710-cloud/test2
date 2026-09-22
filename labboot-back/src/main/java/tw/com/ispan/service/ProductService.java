package tw.com.ispan.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.repository.ProductRepository;
import tw.com.ispan.utils.DatetimeConverter;

@Service
public class ProductService {
	private ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<ProductBean> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.find(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.count(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	public boolean exists(Integer id) {
		return productRepository.existsById(id);
	}
	public ProductBean findById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}
	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if(bean!=null && bean.getId()!=null && !bean.getId().equals(0)) {
			ProductBean temp = productRepository.findById(bean.getId()).orElse(null);
			if(temp!=null) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = productRepository.findAll();
		}
		return result;
	}

	public ProductBean insert(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			if(!productRepository.existsById(bean.getId())) {
				return productRepository.save(bean);
			}
		}
		return null;
	}
	public ProductBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");
			
			if(!productRepository.existsById(id)) {
				ProductBean insert = new ProductBean();
				insert.setId(id);
				insert.setName(name);
				insert.setPrice(price);
				insert.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
				insert.setExpire(expire);
			
				return productRepository.save(insert);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ProductBean update(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			if(productRepository.existsById(bean.getId())) {
				return productRepository.save(bean);
			}
		}
		return null;
	}
	public ProductBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double price = obj.isNull("price") ? null : obj.getDouble("price");
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");

			ProductBean update = productRepository.findById(id).orElse(null);
			update.setName(name);
			update.setPrice(price);
			update.setMake(DatetimeConverter.parse(make, "yyyy-MM-dd"));
			update.setExpire(expire);

			return productRepository.save(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean delete(ProductBean bean) {
		if(bean!=null && bean.getId()!=null) {
			productRepository.deleteById(bean.getId());
			return true;
		}
		return false;
	}
	public boolean remove(Integer id) {
		try {
			productRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
