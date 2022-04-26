package core.ics.person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import core.ics.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	List<Person> findByName(@Param(value = "name") String name);
}
