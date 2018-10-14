package it.worldpay.fede.offersmanager.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import it.worldpay.fede.offersmanager.model.Product;

@NoRepositoryBean
public interface BaseRepository<T extends Product> extends CrudRepository<T, Long> {
	
	Product findByProductId(Long productId);
	
}
