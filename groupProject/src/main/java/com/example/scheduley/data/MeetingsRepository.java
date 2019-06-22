package com.example.scheduley.data;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for meetings
 * @author thien
 *
 */
@Repository
public interface MeetingsRepository extends JpaRepository<Meetings,String> {

	@Query(value = "select * from meetings where location_id = ?1", nativeQuery = true)
	List<Meetings> getAllByMeetingSpaceID(int id);
	
	@Query(value = "select * from meetings where date_time = ?1", nativeQuery = true)
	List<Meetings> getAllByStartTime(Timestamp startTime);
	
	@Query(value = "select * from meetings where date_time = ?1 and location_id = ?2", nativeQuery = true)
	List<Meetings> getAllByStartTimeAndID(Timestamp startTime, int id);
}

