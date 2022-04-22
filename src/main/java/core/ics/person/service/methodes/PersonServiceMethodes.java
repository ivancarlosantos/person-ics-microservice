package core.ics.person.service.methodes;

import java.util.Collection;

import core.ics.person.model.Person;

public interface PersonServiceMethodes {

	Person personSave(Person person);

	Collection<Person> listAll(Person filter);

	Person update(Long id, Person person);
	
	void deleteById(Long id);
	
	void delete(Long id, Person person);
}
