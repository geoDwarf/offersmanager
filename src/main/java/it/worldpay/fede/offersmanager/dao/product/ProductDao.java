package it.worldpay.fede.offersmanager.dao.product;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.dao.repository.BaseRepository;
import it.worldpay.fede.offersmanager.model.product.Product;

@Repository
public interface ProductDao <T extends Product> extends BaseRepository<Product>{

}
