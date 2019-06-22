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
 * Controller class for users
 * @author thien
 *
 * path: "../users"
 * handles anything users related
 */
@RestController
@RequestMapping(path = "/users")
public class UsersController {

	private UsersRepository usersRepository;
	private Logger logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	public UsersController(UsersRepository usersRepository){
		this.usersRepository = usersRepository;
	}
	
	/**
	 * Creates a new user
	 * @param Users object in JSON
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.PUT, path = "/create")
	@ResponseBody
	public Map<String,Object> createUser(@RequestBody Users user) {
		HashMap<String,Object> response = new HashMap<>();

		try {
			if(!usersRepository.getAllByEmail(user.getEmail()).isEmpty())
				throw new IllegalArgumentException();
			
			usersRepository.save(user);
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "User might already exists, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Deletes a user from DB
	 * @param Users object in JSON
	 * @return A JSON status response
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/delete")
	@ResponseBody
	public Map<String,Object> deleteUser(@RequestBody Users user) {
		HashMap<String,Object> response = new HashMap<>();

		try {
			if(usersRepository.getAllByEmail(user.getEmail()).isEmpty())
				throw new IllegalArgumentException();

			Users temp = usersRepository.getAllByEmail(user.getEmail()).get(0);
			usersRepository.deleteById(temp.getID());
			response.put("status", 200);
			response.put("message", HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			response.put("status", 400);
			response.put("error", HttpStatus.BAD_REQUEST);
			response.put("message", "User might not exist, or your fields are incorrect, double check your request");
		}catch (Exception e) {
			response.put("status", 500);
			response.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("message", "Server might be down now. Try again");
		}
		return response;
	}
	
	/**
	 * Get all users info
	 * @return users' email and names as a JSON array, empty if nothing
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/search/all")
	@ResponseBody
	public List<Users> getAllUsersName() {
		return (List<Users>) usersRepository.findAll();
	}
	
	/**
	 * Search a user by ID
	 * @param JSON object of userID
	 * @return A user in JSON, null if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/id")
	@ResponseBody
	public Optional<Users> searchUsersByID(@RequestBody Users user) {
		return usersRepository.findById(user.getID());
	}
	
	/**
	 * Search a user by email
	 * @param JSON object of user's email
	 * @return JSONArray of user(s) matched, empty if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/email")
	@ResponseBody
	public List<Users> searchUsersByEmail(@RequestBody Users user) {
		return usersRepository.getAllByEmail(user.getEmail());
	}
	
	/**
	 * Search a user by name
	 * @param JSON object of user's name
	 * @return JSONArray of user(s) matched, empty if nothing matched
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/search/name")
	@ResponseBody
	public List<Users> searchUsersByName(@RequestBody Users user) {
		return usersRepository.getAllByName(user.getName());
	}
}
