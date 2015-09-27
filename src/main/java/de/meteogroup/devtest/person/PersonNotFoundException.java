package de.meteogroup.devtest.person;

/**
 * Exception thrown when a Person ressource with the given id cannot be found.
 * 
 * @author kuschel
 */
@SuppressWarnings("serial")
public class PersonNotFoundException extends Exception {

	public PersonNotFoundException(final String message) {
		super(message);
	}
}
