package com.example.scheduley.model;

public interface MeetingSpaceDAO {
	
	public MeetingSpace[] getAllMeetingSpace();
	public MeetingSpace[] getMeetingSpaceByFloor(int floorID) throws IllegalArgumentException;
	public int addMeetingSpace(MeetingSpace meetingSpace);
	public int deleteMeetingSpace(MeetingSpace toDelete);
	public boolean isMeetingSpaceInDB(String name);
}
