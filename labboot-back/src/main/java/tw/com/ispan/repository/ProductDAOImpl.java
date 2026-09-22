package tw.com.ispan.repository;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.ispan.domain.ProductBean;
import tw.com.ispan.utils.DatetimeConverter;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@PersistenceContext
	private EntityManager entityManager;
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public List<ProductBean> find(JSONObject obj) {
		if(obj!=null) {
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double minPrice = obj.isNull("minPrice") ? null : obj.getDouble("minPrice");
			Double maxPrice = obj.isNull("maxPrice") ? null : obj.getDouble("maxPrice");
			String minMake = obj.isNull("minMake") ? null : obj.getString("minMake");
			String maxMake = obj.isNull("maxMake") ? null : obj.getString("maxMake");
			Integer minExpire = obj.isNull("minExpire") ? null : obj.getInt("minExpire");
			Integer maxExpire = obj.isNull("maxExpire") ? null : obj.getInt("maxExpire");

			int start = obj.isNull("start") ? 0 : obj.getInt("start");
			int rows = obj.isNull("rows") ? 3 : obj.getInt("rows");
			String order = obj.isNull("order") ? "id" : obj.getString("order");
			boolean dir = obj.isNull("dir") ? false : obj.getBoolean("dir");
			
			CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ProductBean> criteriaQuery = criteriaBuilder.createQuery(ProductBean.class);
			
//			from product
			Root<ProductBean> table = criteriaQuery.from(ProductBean.class);
			
//			where ...
			List<Predicate> predicates = new ArrayList<>();
			if(id!=null) {
				predicates.add(	 criteriaBuilder.equal(table.get("id"), id)  );
			}
			if(name!=null && name.length()!=0) {
				predicates.add(  criteriaBuilder.like(table.get("name"), "%"+name+"%")  );
			}
			if(minPrice!=null) {
				predicates.add(  criteriaBuilder.greaterThan(table.get("price"), minPrice)  );
			}
			if(maxPrice!=null) {
				predicates.add(  criteriaBuilder.lessThan(table.get("price"), maxPrice)  );
			}
			if(minMake!=null) {
				java.util.Date date = DatetimeConverter.parse(minMake, "yyyy-MM-dd");
				predicates.add(  criteriaBuilder.greaterThan(table.get("make"), date)  );
			}
			if(maxMake!=null) {
				java.util.Date date = DatetimeConverter.parse(maxMake, "yyyy-MM-dd");
				predicates.add(  criteriaBuilder.lessThan(table.get("expire"), date)  );
			}
			if(minExpire!=null) {
				predicates.add(  criteriaBuilder.greaterThan(table.get("expire"), minExpire)  );
			}
			if(maxExpire!=null) {
				predicates.add(  criteriaBuilder.lessThan(table.get("expire"), maxExpire)  );
			}
			criteriaQuery = criteriaQuery.where(predicates);
			
//			order by
			if(dir) {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.desc(table.get(order)));
			} else {
				criteriaQuery = criteriaQuery.orderBy(criteriaBuilder.asc(table.get(order)));
			}
			
			TypedQuery<ProductBean> typedQuery = this.getEntityManager().createQuery(criteriaQuery)
					.setFirstResult(start)
					.setMaxResults(rows);
			List<ProductBean> results = typedQuery.getResultList();
			if(results!=null && !results.isEmpty()) {
				return results;
			}
		}
		return null;
	}
	@Override
	public Long count(JSONObject obj) {
		if(obj!=null) {
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			Double minPrice = obj.isNull("minPrice") ? null : obj.getDouble("minPrice");
			Double maxPrice = obj.isNull("maxPrice") ? null : obj.getDouble("maxPrice");
			String minMake = obj.isNull("minMake") ? null : obj.getString("minMake");
			String maxMake = obj.isNull("maxMake") ? null : obj.getString("maxMake");
			Integer minExpire = obj.isNull("minExpire") ? null : obj.getInt("minExpire");
			Integer maxExpire = obj.isNull("maxExpire") ? null : obj.getInt("maxExpire");

			CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

//			from product
			Root<ProductBean> table = criteriaQuery.from(ProductBean.class);

//			where ...
			List<Predicate> predicates = new ArrayList<>();
			if(id!=null) {
				predicates.add(	 criteriaBuilder.equal(table.get("id"), id)  );
			}
			if(name!=null && name.length()!=0) {
				predicates.add(  criteriaBuilder.like(table.get("name"), "%"+name+"%")  );
			}
			if(minPrice!=null) {
				predicates.add(  criteriaBuilder.greaterThan(table.get("price"), minPrice)  );
			}
			if(maxPrice!=null) {
				predicates.add(  criteriaBuilder.lessThan(table.get("price"), maxPrice)  );
			}
			if(minMake!=null) {
				java.util.Date date = DatetimeConverter.parse(minMake, "yyyy-MM-dd");
				predicates.add(  criteriaBuilder.greaterThan(table.get("make"), date)  );
			}
			if(maxMake!=null) {
				java.util.Date date = DatetimeConverter.parse(maxMake, "yyyy-MM-dd");
				predicates.add(  criteriaBuilder.lessThan(table.get("expire"), date)  );
			}
			if(minExpire!=null) {
				predicates.add(  criteriaBuilder.greaterThan(table.get("expire"), minExpire)  );
			}
			if(maxExpire!=null) {
				predicates.add(  criteriaBuilder.lessThan(table.get("expire"), maxExpire)  );
			}
			criteriaQuery = criteriaQuery.where(predicates);
			
//			select count(*)
			criteriaQuery = criteriaQuery.select(criteriaBuilder.count(table));
			
			TypedQuery<Long> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
			return typedQuery.getSingleResult();
		}
		return 0L;
	}
	
}
