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
 * Controller class for floor
 * @author thien
 *
 * path: "../floor"
 * handles anything floor related
 */
@RestController
@RequestMapping(path = "/floor")
public class FloorController {

	private FloorRepository floorRepository;
	private Logger logger = LoggerFactory.getLogger(FloorController.class);
	
	@Autowired
	public FloorController(FloorRepository floorRepository){
		this.floorRepository = floorRepository;
	}
	
	/**
	 * Creates a new floor
	 * @param Floor name and URL in JSON
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/create")
	@ResponseBody
	public Map<String,Object> createFloor(@RequestBody Floor floor) {
		HashMap<String,Object> response = new HashMap<>();
		Optional<Floor> temp = floorRepository.findById(floor.getFloorID());
		try {
			if(temp.isPresent())
				throw new IllegalArgumentException();
			
			floorRepository.save(floor);
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Floor might already exists, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Deletes an existing floor
	 * @param FloorID in JSONObject
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public Map<String,Object> deleteFloor(@RequestBody Floor floor) {
		HashMap<String,Object> response = new HashMap<>();
		Optional<Floor> temp = floorRepository.findById(floor.getFloorID());
		try {
			if(!temp.isPresent())
				throw new IllegalArgumentException();
			
			floorRepository.deleteById(floor.getFloorID());
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "Floor might not exist, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Get all floor info
	 * @return floors as a JSON array, empty if nothing
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/search/all")
	@ResponseBody
	public List<Floor> getAllFloors() {
		return (List<Floor>) floorRepository.findAll();
	}
}
