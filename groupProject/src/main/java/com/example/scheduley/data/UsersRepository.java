package com.example.scheduley.data;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for users
 * @author thien
 *
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

	@Query(value = "select * from users where email = ?1", nativeQuery = true)
	List<Users> getAllByEmail(String email);
	
	@Query(value = "select * from users where name = ?1", nativeQuery = true)
	List<Users> getAllByName(String name);
}
