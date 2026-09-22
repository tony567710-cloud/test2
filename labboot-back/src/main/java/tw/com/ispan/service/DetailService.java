package tw.com.ispan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.domain.DetailBean;
import tw.com.ispan.repository.DetailRepository;

@Service
@Transactional
public class DetailService {
	private DetailRepository detailRepository;
	public DetailService(DetailRepository detailRepository) {
		this.detailRepository = detailRepository;
	}

	public DetailBean findById(Integer id) {
		if (id != null) {
			return detailRepository.findById(id).orElse(null);
		}
		return null;
	}
}
