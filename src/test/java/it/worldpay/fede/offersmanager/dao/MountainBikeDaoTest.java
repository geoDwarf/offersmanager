package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MountainBikeDaoTest {
	
	
	@Before
	public void initializeTestVariable(){
		mountainBikeDummy = (MountainBike)dummyFactory.getDummyProduct("MOUNTAINBIKE");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	MountainBikeDao mountainBikeDao;
	
	private MountainBike mountainBikeDummy;
	
	private MountainBike mountainBikeFound;
	
	private MountainBike mountainBikeSaved;
	
	@Test
	public void whenSaveMountainBike_thenItIsPossibleToFetchIt(){
		
		mountainBikeDao.save(mountainBikeDummy);
		
		mountainBikeFound = mountainBikeDao.findOne(mountainBikeDummy.getProductId());
		
		assertEquals(mountainBikeFound.getProductId(), mountainBikeDummy.getProductId());
			}

	@Test
	public void whenSaveMountainBike_thenItIsPossibleToDeleteIt(){
		
		mountainBikeDao.save(mountainBikeDummy);
		
		mountainBikeDao.delete(mountainBikeDummy);
		
		mountainBikeFound = mountainBikeDao.findOne(mountainBikeDummy.getProductId());
		
		Assert.assertNull(mountainBikeFound);
	}
	
	

	@Test
	public void whneMountainBikeIsSaved_thenItIspossibleToFetchItById(){
		
		mountainBikeSaved =mountainBikeDao.save(mountainBikeDummy);
		
		Long mountainBikeSavedId = mountainBikeSaved.getProductId();
		
		mountainBikeFound = (MountainBike)mountainBikeDao.findByProductId(mountainBikeSavedId);
		}

}
