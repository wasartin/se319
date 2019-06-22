package com.example.scheduley.data;

import javax.persistence.*;

/**
 * Class for table meeting_space in DB
 * @author thien
 * table meeting_space: (meeting_space_id,x,y,width,height,floor_id)
 */
@Entity
@Table(name = "meeting_space")
public class MeetingSpace {
	
	/**
	 * Primary key
	 * int auto_increment
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meeting_space_id")
	private int meetingSpaceID;
	
	/**
	 * name
	 * varchar(100)
	 */
	@Column(name = "meeting_space_name")
	private String meetingSpaceName;
	
	/**
	 * x-coordinate
	 * int
	 */
	@Column(name = "x", nullable = false)
    private int x;
	
	/**
	 * y-coordinate
	 * int
	 */
	@Column(name = "y", nullable = false)
    private int y;
	
	/**
	 * int
	 */
	@Column(name = "width", nullable = false)
    private int width;
	
	/**
	 * int
	 */
	@Column(name = "height", nullable = false)
    private int height;
	
	/**
	 * NOT foreign key
	 * int
	 */
	@Column(name = "floor_id", nullable = false)
    private int floorID;
	
	/**
	 * Get meeting space ID
	 * @return meeting space ID
	 */
	public int getMeetingSpaceID() {
		return this.meetingSpaceID;
	}
	
	/**
	 * Get meeting space name
	 * @return meeting space name
	 */
	public String getMeetingSpaceName() {
		return this.meetingSpaceName;
	}
	
	/**
	 * Get x-coordinate
	 * @return x-coordinate
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Get y-coordinate
	 * @return y-coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Get width
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Get height
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Get floor ID
	 * @return floor ID
	 */
	public int getFloorID() {
		return this.floorID;
	}
}
