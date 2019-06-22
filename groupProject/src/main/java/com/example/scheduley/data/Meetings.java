package com.example.scheduley.data;

import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Class for meetings table in DB
 * @author thien
 * table meetings: (meeting_name,date_time,duration,location_id)
 */
@Entity
@Table(name="meetings")
public class Meetings {
	
	/**
	 * primary key
	 * varchar(100)
	 */
	@Id
	@Column(name = "meeting_name", nullable = false, unique = true)
    private String meetingName;
	
	/**
	 * Start time
	 * datetime
	 */
	@Column(name = "date_time", nullable = false)
    private Timestamp dateTime;
	
	/**
	 * Duration in minutes
	 * int
	 */
	@Column(name = "duration")
	private int duration;
	
	/**
	 * Location ID
	 * int
	 */
	@Column(name = "location_id", nullable = false)
	private int location;
	
	/**
	 * get a meeting's name
	 * @return meeting name in String
	 */
	public String getMeetingName() {
		return this.meetingName;
	}
	
	/**
	 * get a meeting's date and time
	 * @return date and time in Timestamp
	 */
	public Timestamp getDateTime() {
		return this.dateTime;
	}
	
	/**
	 * get a meeting's duration
	 * @return duration in int
	 */
	public int getDuration() {
		return this.duration;
	}
	
	/**
	 * get a meeting's location
	 * @return location in int
	 */
	public int getLocation() {
		return this.location;
	}
	
	/**
	 * change a meeting's name
	 * only for internal purposes
	 * @param mname
	 */
	protected void setMeetingName(String mname) {
		this.meetingName = mname;
	}
	
	/**
	 * change a meeting's date and time
	 * only for internal purposes
	 * @param timestamp
	 */
	protected void setDateTime(Timestamp timestamp) {
		this.dateTime = timestamp;
	}
	
	/**
	 * change a meeting's duration
	 * only for internal purposes
	 * @param duration
	 */
	protected void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * change a meeting's location
	 * only for internal purposes
	 * @param location
	 */
	protected void setLocation(int location) {
		this.location = location;
	}
}
