package com.example.scheduley.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Controller class for meeting space
 * @author thien
 *
 * path: "../meetingspace"
 * handles anything meeting space related
 */
@RestController
@RequestMapping(path = "/meetingspace")
public class MeetingSpaceController {

	private MeetingSpaceRepository meetingSpaceRepository;
	private Logger logger = LoggerFactory.getLogger(MeetingSpaceController.class);
	
	@Autowired
	public MeetingSpaceController(MeetingSpaceRepository meetingSpaceRepository){
		this.meetingSpaceRepository = meetingSpaceRepository;
	}
	
	/**
	 * Creates a new meeting space
	 * @param Meeting space object in JSON
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/create")
	@ResponseBody
	public Map<String,Object> createMeetingSpace(@RequestBody MeetingSpace meetingspace) {
		HashMap<String,Object> response = new HashMap<>();
		List<MeetingSpace> temp = meetingSpaceRepository.getAllByCoordinates(meetingspace.getX(), meetingspace.getY(), meetingspace.getFloorID());
		try {
			if(!temp.isEmpty())
				throw new IllegalArgumentException();
			
			meetingSpaceRepository.save(meetingspace);
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Meeting space might already exists, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Deletes an existing meeting space
	 * @param Meeting space's x,y,floorID in JSONObject
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public Map<String,Object> deleteMeetingSpace(@RequestBody MeetingSpace meetingspace) {
		HashMap<String,Object> response = new HashMap<>();
		List<MeetingSpace> temp = meetingSpaceRepository.getAllByCoordinates(meetingspace.getX(), meetingspace.getY(), meetingspace.getFloorID());
		try {
			if(temp.isEmpty())
				throw new IllegalArgumentException();
			
			meetingSpaceRepository.deleteById(temp.get(0).getMeetingSpaceID());
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Meeting space might not exist, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Get all meeting space info
	 * @return meeting spaces as a JSON array, empty if nothing
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/search/all")
	@ResponseBody
	public List<MeetingSpace> getAllMeetingSpace() {
		return (List<MeetingSpace>) meetingSpaceRepository.findAll();
	}
}
