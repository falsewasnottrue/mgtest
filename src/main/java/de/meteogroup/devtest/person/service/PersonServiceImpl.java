package de.meteogroup.devtest.person.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import de.meteogroup.devtest.person.Person;
import de.meteogroup.devtest.person.PersonNotFoundException;
import de.meteogroup.devtest.person.dao.PersonDAO;

/**
 * Simple mock implementation of the PersonService interface.
 * 
 * Tries to load resources from files on the classpath.
 * Stores resources in a in-memory-datastructure 
 *
 * @author kuschel
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Inject private PersonDAO personDAO;
	
	// Internal cache to mock store operations
	private Map<String, Person> personCache = new HashMap<String, Person>();
	
	/**
	 * {@inheritDoc}
	 */
	public Person loadPerson(final String id) throws PersonNotFoundException {
		
		// try to load person from data file
		Person person = personDAO.loadPerson(id);
		
		if (person == null) {
			// no matching data file found, check internal cache
			person = personCache.get(id);
		}
		
		if (person == null) {
			// no matching person resource in data file or cache found
			throw new PersonNotFoundException("No Person resource available with id " + id);
		}
		
		return person;
	}

	/**
	 * {@inheritDoc}
	 */
	public void savePerson(final Person person) {
		if (person == null || person.getId() == null) {
			// throw RuntimeException that can be handled ExceptionResolver
			throw new IllegalStateException("Attempt to store incomplete person ressource");
		}
		
		personCache.put(person.getId(), person);
	}
}
