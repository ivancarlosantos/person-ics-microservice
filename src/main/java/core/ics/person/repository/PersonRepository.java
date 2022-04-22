package core.ics.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.ics.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
}
