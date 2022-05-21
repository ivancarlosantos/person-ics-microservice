package core.ics.person.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import core.ics.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM person q WHERE q.person_name LIKE %?1%")
	Optional<Person> fetchByName(@Param(value = "personName") String personName);
	
	@Query(nativeQuery = true, value = "SELECT * FROM person q WHERE q.address LIKE %?1%")
	Optional<Person> fetchAddress(@Param(value = "address") String address);
}
