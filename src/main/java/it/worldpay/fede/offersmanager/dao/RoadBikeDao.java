package it.worldpay.fede.offersmanager.dao;

import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.dao.repository.BaseRepository;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;

@Repository
public interface RoadBikeDao extends BaseRepository<RoadBike> {

}
