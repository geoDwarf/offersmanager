package it.worldpay.fede.offersmanager.dao;

import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.dao.repository.BaseRepository;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Repository
public interface GelatoDao<T extends Product> extends BaseRepository<Gelato>
{
		
	
	}
