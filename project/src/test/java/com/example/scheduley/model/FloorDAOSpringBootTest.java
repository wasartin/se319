package com.example.scheduley.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FloorDAOSpringBootTest {
	
	public static DAOFactory springBootDBFactory;
	public static FloorDAO floorDAO;

	@BeforeAll
	public static void setUp() throws Exception {
		springBootDBFactory = DAOFactory.getDAOFactory(DAOFactory.SPRING_BOOT);
		floorDAO = springBootDBFactory.getFloorDAO();
	}

	@Test
	void getFloorByIDTest_Name() {
		Floor f = floorDAO.getFloorByID(672);
		Assert.assertTrue(f.getName().equalsIgnoreCase("floor 29") || f.getName() == "floor 29");
	}
	
	@Test
	void getFloorByIDTest_URL() {
		Floor f = floorDAO.getFloorByID(672);
		Assert.assertTrue(f.getImageURL().equalsIgnoreCase("main/MrScheduley.png"));
	}
	
}
