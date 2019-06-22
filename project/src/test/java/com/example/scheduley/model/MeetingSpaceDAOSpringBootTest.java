package com.example.scheduley.model;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class MeetingSpaceDAOSpringBootTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none(); //This is fantastic
	
	public static DAOFactory springBootDBFactory;
	public static MeetingSpaceDAO meetingSpaceDAO;
	
	public static MeetingSpace creativeSpace;
	public static MeetingSpace lab;
	public static MeetingSpace breakArea;
	public static MeetingSpace retreat;
	public static MeetingSpace tla;
	
	public static MeetingSpace notInDB;

	@BeforeAll
	public static void setUp() {
		springBootDBFactory = DAOFactory.getDAOFactory(DAOFactory.SPRING_BOOT);
		meetingSpaceDAO = springBootDBFactory.getMeetingSpaceDAO();
		
		creativeSpace = new MeetingSpace("Meetings/Creative Space", 88, 40, 213, 113, 671);
		creativeSpace.setUniqueID(5);
		
		lab = new MeetingSpace("Conference/Lab Room", 88, 153, 139, 119, 672);
		lab.setUniqueID(6);
		
		breakArea = new MeetingSpace("Break Area", 307.0, 47.0, 61.0, 101.0, 673);
		breakArea.setUniqueID(7);
		
		retreat = new MeetingSpace("Retreat", 471.0, 283.0, 115.0, 114.0, 673);
		retreat.setUniqueID(8);
		
		tla = new MeetingSpace("TLA", 200, 300, 50, 60, 678);
		tla.setUniqueID(1);
		
		notInDB =  new MeetingSpace("Dean's Office", 30, 50, 100, 40, 1);
	}
	
	/**
	 * This method is only testing the first 5 meetingSpaces in the DB, otherwise we would have to add another meeting space to test this
	 * every time.
	 * 
	 * If we had more time, it would be great to just have another DB table just for testing.
	 */
	@Test
	public void getAllMeetingSpace_Success() {
		MeetingSpace[] expected = new MeetingSpace[5];
		expected[0] = tla;
		expected[1] = creativeSpace;
		expected[2] = lab;
		expected[3] = breakArea;
		expected[4] = retreat;
		
		MeetingSpace[] actual = meetingSpaceDAO.getAllMeetingSpace();
		
		for(int i = 0; i < expected.length; i++) {
			Assert.assertTrue(expected[i].equals(actual[i]));
		}
	}
	
	@Test
	public void getMeetingSpaceByFloor_Success() throws IllegalArgumentException {
		MeetingSpace[] expected = new MeetingSpace[4];
		expected[0] = creativeSpace;
		expected[1] = lab;
		expected[2] = breakArea;
		expected[3] = retreat;
		
		MeetingSpace[] actual = meetingSpaceDAO.getMeetingSpaceByFloor(1);
		
		for(int i = 0; i < expected.length; i++) {
			Assert.assertTrue(expected[i].equals(actual[i]));
		}
	}
	
	@Test
	public void getMeetingSpaceByFloor_Fail_ExceptionThrown() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		meetingSpaceDAO.getMeetingSpaceByFloor(0);
	}
	
	@Test
	public void isMeetingSpaceInDB_Success() {
		Assert.assertTrue(meetingSpaceDAO.isMeetingSpaceInDB(creativeSpace.getName()));
	}
	
	@Test
	public void isMeetingSpaceInDB_Fail() {
		Assert.assertFalse(meetingSpaceDAO.isMeetingSpaceInDB(notInDB.getName()));
	}
	
	@Test
	public void addMeetingSpace_Success() {
		MeetingSpace toAdd = new MeetingSpace("Dean Office", 30, 50, 100, 40, 1);
		int actualResult = meetingSpaceDAO.addMeetingSpace(toAdd);
		int expectedResult = 200; //This method returns 200 upon success
		Assert.assertTrue(meetingSpaceDAO.isMeetingSpaceInDB(toAdd.getName())); 
		Assert.assertEquals(expectedResult, actualResult);
		//clean up
		meetingSpaceDAO.deleteMeetingSpace(toAdd);
	}
	
	@Test
	public void addMeetingSpace_Fail() {
		Assert.assertEquals(400, meetingSpaceDAO.addMeetingSpace(creativeSpace));

	}
	
	@Test
	public void deleteMeetingSpace_Success() {
		//add something to delete
		MeetingSpace toAdd = new MeetingSpace("Dean Office's", 30, 50, 100, 40, 1);
		meetingSpaceDAO.addMeetingSpace(toAdd);
		
		int actualResult = meetingSpaceDAO.deleteMeetingSpace(toAdd);
		int expectedResult = 200; 
		Assert.assertEquals(expectedResult, actualResult);
		Assert.assertFalse(meetingSpaceDAO.isMeetingSpaceInDB(toAdd.getName()));
	}
	
	@Test
	public void deleteMeetingSpace_Fail() {
		int actualResult = meetingSpaceDAO.deleteMeetingSpace(notInDB);
		int expectedResult = 400; 
		Assert.assertEquals(expectedResult, actualResult);
	}
}
