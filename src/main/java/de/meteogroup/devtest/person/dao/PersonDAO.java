package de.meteogroup.devtest.person.dao;

import de.meteogroup.devtest.person.Person;

public interface PersonDAO {

	Person loadPerson(final String id);
}
