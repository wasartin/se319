package com.example.scheduley.model;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class UserDAOMySQLTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	public static DAOFactory jdbcDBFactory;
	public static UserDAO dataSource;
	
	public static UserProfile notInDB;
	
	public static UserProfile theBestGuyEver;
	public static UserProfile will;
	public static UserProfile Jeremiah;
	public static UserProfile putin;
	
	@BeforeAll
	public static void setUp() {
		jdbcDBFactory = DAOFactory.getDAOFactory(DAOFactory.JDBC);
		dataSource = jdbcDBFactory.getUserDAO();
		
		notInDB = new UserProfile("Dwayne_The_Rock_Johnson@iastate.edu", "Hannah Dakota", UserType.OTHER);
		will = new UserProfile("wasartin@iastate.edu", "Will Sartin", 0);
		theBestGuyEver  = new UserProfile("nht209@iastate.edu", "Thien Nguyen", 0);
		Jeremiah = new UserProfile("jonathanJohnson@tom.com", "John Johnson", UserType.OTHER);
		putin = new UserProfile("putin@iastate.edu", "Vladimir Putin", UserType.OTHER);
	}
	
	
	@Test
	public void insertUser() throws SQLException {
		UserProfile tom = new UserProfile("tomDodge@tom.com", "Tom Dodge", UserType.ADMIN);
		dataSource.insertUser(tom);
		Assert.assertTrue(dataSource.verifyUser(tom.getEmail(), tom.getPassword()));
		
		//Clean up
		dataSource.deleteUser("tomDodge@tom.com");	
	}
	
	/**
	 * This Test only tests the first 4 users. Since we would have to update this test every single time
	 * there are new users added to the DB. 
	 * @throws SQLException
	 */
	@Test
	public void findAllUserTest() throws SQLException {
		ArrayList<UserProfile> users = dataSource.findAllUsers();
		ArrayList<UserProfile> expectedUsers = new ArrayList<UserProfile>();
		expectedUsers.add(theBestGuyEver);
		expectedUsers.add(Jeremiah);
		expectedUsers.add(will);
		expectedUsers.add(putin);
		
		for(int i = 0; i < expectedUsers.size(); i++) {
			Assert.assertTrue(expectedUsers.get(i).equals(users.get(i)));
		}
	}

	
	@Test
	public void deleteUser() throws SQLException {
		UserProfile tom = new UserProfile("tomDodge@tom.com", "Tom Dodge", UserType.ADMIN);
		//Add User
		dataSource.insertUser(tom);
		//Delete User
		dataSource.deleteUser(tom.getEmail());

		Assert.assertFalse(dataSource.verifyUser(tom.getEmail(), tom.getPassword()));
	}
	
	@Test
	public void deleteUser_Fail()throws SQLException{
		thrown.expect(SQLException.class);
		dataSource.deleteUser(notInDB.getEmail());
	}
	
	@Test
	public void verifyUserTest() {
		Assert.assertTrue(dataSource.verifyUser(will.getEmail(), will.getPassword()));
	}
	
	@Test
	public void verifyUserTest_Fail_UserNotInDB() {
		Assert.assertFalse(dataSource.verifyUser(notInDB.getEmail(), notInDB.getPassword()));
	}
	
	@Test
	public void verifyUserTest_Fail_BadEmail() {
		Assert.assertFalse(dataSource.verifyUser("NotAEmailInDB", will.getPassword()));
	}
	
	@Test
	public void verifyUserTest_Fail_BadPassword() {
		Assert.assertFalse(dataSource.verifyUser(will.getEmail(), "asdf"));
	}
	
	@Test
	public void findUserTest() {
		String theBestGuyEver = "nht209@iastate.edu";
		UserProfile expected = new UserProfile(theBestGuyEver, "Thien Nguyen", 0);
		Assert.assertTrue(expected.equals(dataSource.findUser(theBestGuyEver)));
	}
}