package it.worldpay.fede.offersmanager.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.dao.repository.BaseRepository;
import it.worldpay.fede.offersmanager.model.Product;

@Repository
public interface ProductDao <T extends Product> extends BaseRepository<Product>{

}
