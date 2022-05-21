package core.ics.person.service.methodes;

import java.util.List;
import java.util.Optional;

import core.ics.person.model.Person;

public interface PersonServiceMethodes {

	Person personSave(Person person);

	List<Person> listAll(Person filter);
	
	Optional<Person> fetchName(String name);

	Person update(Long id, Person person);
	
	void deleteById(Long id);
	
	void delete(Long id, Person person);
}
