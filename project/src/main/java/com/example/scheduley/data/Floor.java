package com.example.scheduley.data;

import javax.persistence.*;

/**
 * Class for table floor in DB
 * @author thien
 * table floor: (floor_id,floor_name,image_url)
 */
@Entity
@Table(name = "floor")
public class Floor {
	
	/**
	 * Primary key
	 * int auto_increment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "floor_id")
	private int floorID;
	
	/**
	 * name
	 * varchar(100)
	 */
	@Column(name = "floor_name", nullable = false)
	private String floorName;
	
	/**
	 * Image URL
	 * varchar(2083)
	 */
	@Column(name = "image_url", nullable = false)
	private String imgURL;
	
	/**
	 * Get floor ID
	 * @return floor ID
	 */
	public int getFloorID() {
		return this.floorID;
	}
	
	/**
	 * Get floor name
	 * @return floor name
	 */
	public String getFloorName() {
		return this.floorName;
	}
	
	/**
	 * Get image URL
	 * @return image URL
	 */
	public String getImgURL() {
		return this.imgURL;
	}
	
	
}
