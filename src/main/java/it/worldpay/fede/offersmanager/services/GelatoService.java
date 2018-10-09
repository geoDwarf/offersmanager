package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Gelato;


@Service
public interface GelatoService {
	
//	Gelato getGelato(Long id);
	
	void saveGelato(Gelato gelato) throws DuplicateProductException;
	
//	void deleteGelato(Gelato gelato)throws ProductNotFoundException;
}
