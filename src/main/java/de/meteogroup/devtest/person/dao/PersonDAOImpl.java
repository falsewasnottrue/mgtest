package de.meteogroup.devtest.person.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import de.meteogroup.devtest.person.Person;

/**
 * Implementation of the PersonDAO interface that reads Person resources from files on the classpath
 * 
 * @author kuschel
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

	private final static Logger LOG = LoggerFactory.getLogger(PersonDAOImpl.class);
	
	private final static String PATH = "de/meteogroup/devtest/person/data/";
	private final static String FILE_NAME_PATTERN = "person-ID.txt";
	
	public Person loadPerson(final String id) {
		
		// check if a matching person data file exists on the class path
		final String filename = FILE_NAME_PATTERN.replace("ID", id);
		final InputStream is = PersonDAOImpl.class.getClassLoader().getResourceAsStream(PATH + filename);
		if (is == null) {
			// no resource found on the classpath 
			return null;
		}
		
		// load the data
		final Properties personData = new Properties();
		try {
			personData.load(is);
		} catch (final IOException e) {
			LOG.error("Error while loading data file", e);
			return null;
		}

		// convert it to a person object
		Person person = new Person(personData);
		return person;
	}

}
