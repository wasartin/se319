package com.example.scheduley.data;

import javax.persistence.*;

/**
 * Class for table users in DB.
 * @author thien
 * table users: (user_id,email,password,name,user_type)
 */
@Entity
@Table(name = "users")
public class Users {
	
	/**
	 * primary key
	 * int auto_increment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userID;
	
	/**
	 * varchar(30)
	 */
	@Column(name = "email", nullable = false, unique = true)
    private String email;
	
	/**
	 * varchar (20)
	 */
	@Column(name = "password", nullable = false)
    private String password;
	
	
	/**
	 * varchar(50)
	 */
	@Column(name = "name", nullable = false)
    private String name;
	
	/**
	 * varchar(8)
	 */
	@Column(name = "user_type", nullable = false)
    private String userType;
	
	/**
	 * get user ID
	 * @return user ID
	 */
	public int getID() {
		return this.userID;
	}
	
	/**
	 * get user's email
	 * @return user email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * get user's name
	 * @return user's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * change a user's name
	 * only for internal purposes
	 * @param name
	 */
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * change a user's email
	 * only for internal purposes
	 * @param email
	 */
	protected void setEmail(String email) {
		this.email = email;
	}

	/**
	 * change a user's password
	 * only for internal purposes
	 * @param email
	 */
	protected void setPassword(String password) {
		this.password = password;
	}
	

	/**
	 * change a user's user type
	 * only for internal purposes
	 * @param email
	 */
	protected void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * change a user's id
	 * only for internal purposes
	 * @param id
	 */
	protected void setID(int id) {
		this.userID = id;
	}
	
	/**
	 * get a user's usertype
	 * @return user type
	 */
	public String getUsertype() {
		return this.userType;
	}
}
