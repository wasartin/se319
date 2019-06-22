package com.example.scheduley.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for meeting space
 * @author thien
 *
 */
public interface MeetingSpaceRepository extends JpaRepository<MeetingSpace,Integer>{
	
	@Query(value = "select * from meeting_space where x = ?1 and y = ?2 and floor_id = ?3", nativeQuery = true)
	List<MeetingSpace> getAllByCoordinates(int x,int y,int floor_id);
}
