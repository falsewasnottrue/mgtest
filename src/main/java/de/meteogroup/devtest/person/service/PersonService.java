package de.meteogroup.devtest.person.service;

import de.meteogroup.devtest.person.Person;
import de.meteogroup.devtest.person.PersonNotFoundException;

/**
 * Interface to a backend service that can serve and store Person resources.
 * 
 * @author kuschel
 */
public interface PersonService {
	
	/**
	 * Returns the Person ressource with the specified id.
	 * Throws a PersonNotFoundException when no applicable resource can be found.
	 * 
	 * @param id ID of the Person resource
	 * @return Person resource
	 * @throws PersonNotFoundException when no resource can be found
	 */
	Person loadPerson(final String id) throws PersonNotFoundException;
	
	/**
	 * Stores the Person resource.
	 * 
	 * @param person resource to be stored
	 */
	void savePerson(final Person person);
	

}
