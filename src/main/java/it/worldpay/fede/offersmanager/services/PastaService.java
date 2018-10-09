package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Pasta;

@Service
public interface PastaService {
	
	//Pasta getPasta(Long id);
	
	void savePasta(Pasta pasta) throws DuplicateProductException;
	
	//void deletePasta(Pasta pasta)throws ProductNotFoundException;

}
