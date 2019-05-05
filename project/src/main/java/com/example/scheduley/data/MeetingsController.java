package com.example.scheduley.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for meetings
 * @author thien
 *
 * path: "../meetings"
 * handles anything meetings related
 */
@RestController
@RequestMapping(path = "/meetings")
public class MeetingsController {
	
	private MeetingsRepository meetingsRepository;
	private Logger logger = LoggerFactory.getLogger(MeetingsController.class);
	
	@Autowired
	public MeetingsController(MeetingsRepository meetingsRepository){
		this.meetingsRepository = meetingsRepository;
	}
	
	/**
	 * Create a new meeting to the server
	 * @param Meeting object in JSON
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/create")
	@ResponseBody
	public Map<String,Object> createMeeting(@RequestBody Meetings meeting) {
		HashMap<String,Object> response = new HashMap<>();
		
		try {
			if(meetingsRepository.existsById(meeting.getMeetingName()))
					throw new IllegalArgumentException();
			
			meetingsRepository.save(meeting);
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Meeting might already exists, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Delete an existing meeting from the server, case-insensitive
	 * @param JSON object of the meeting name
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public Map<String,Object> deleteMeeting(@RequestBody Meetings meeting) {
		HashMap<String,Object> response = new HashMap<>();
		
		try {
			if(!meetingsRepository.existsById(meeting.getMeetingName()))
				throw new IllegalArgumentException();
			
			meetingsRepository.deleteById(meeting.getMeetingName());
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Meeting might not exist, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Change a meeting's location or starting time
	 * Name is NOT changeable since it's a primary key
	 * NOT for creating a new meeting
	 * @param JSON object of an updated meeting object
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.PATCH, path = "/patch")
	@ResponseBody
	public Map<String,Object> fixMeeting(@RequestBody Meetings meeting) {
		HashMap<String,Object> response = new HashMap<>();
		
		try {
			if(!meetingsRepository.existsById(meeting.getMeetingName()))
				throw new IllegalArgumentException();
			
			meetingsRepository.save(meeting);
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Meeting might not exist, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Find all meetings scheduled on the server
	 * @return JSON array of all meetings, empty if nothing
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/search/all")
	@ResponseBody
	public List<Meetings> getAllMeetings() {
		return (List<Meetings>) meetingsRepository.findAll();
	}
	
	/**
	 * Find a meeting by its name, case-insensitive
	 * @param JSON object of a meeting's name
	 * @return JSON object of the meeting, null if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/name")
	@ResponseBody
	public Optional<Meetings> searchMeetingsByName(@RequestBody Meetings meeting) {
		return meetingsRepository.findById(meeting.getMeetingName());
	}
	
	/**
	 * Find a meeting by meeting space ID
	 * @param JSON object of meeting's meetingspaceid (location)
	 * @return JSONArray of meetings matched, empty if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/meetingspaceid")
	@ResponseBody
	public List<Meetings> searchMeetingsByMeetingSpaceID(@RequestBody Meetings meeting) {
		return meetingsRepository.getAllByMeetingSpaceID(meeting.getLocation());
	}
	
	/**
	 * Find a meeting by start time
	 * @param JSON object of meeting's start time
	 * @return JSONArray of meetings matched, empty if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/start")
	@ResponseBody
	public List<Meetings> searchMeetingsByTime(@RequestBody Meetings meeting) {
		return meetingsRepository.getAllByStartTime(meeting.getDateTime());
	}
	
	/**
	 * Find a meeting by both start time and meetingspaceid
	 * @param JSON object of meeting's start time and meetingspaceid
	 * @return JSONArray of meeting(s) matched, empty if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/spacetime")
	@ResponseBody
	public List<Meetings> searchMeetingsByLocationAndTime(@RequestBody Meetings meeting) {
		return meetingsRepository.getAllByStartTimeAndID(meeting.getDateTime(), meeting.getLocation());
	}
}
