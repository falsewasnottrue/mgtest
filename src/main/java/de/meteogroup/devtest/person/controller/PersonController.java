package de.meteogroup.devtest.person.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.meteogroup.devtest.person.Person;
import de.meteogroup.devtest.person.PersonNotFoundException;
import de.meteogroup.devtest.person.service.PersonService;

/**
 * Spring MVC controller that handles REST requests for Person resources.
 * 
 * @author kuschel
 */
@Controller
@RequestMapping(value="/myservice")
public class PersonController {

	private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);
	
	@Inject PersonService personService;
	
	@RequestMapping(
			value="/person/{id}", 
			method=RequestMethod.GET,
			produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
	)
	@ResponseBody
	public Person loadPerson(@PathVariable String id) {
		LOG.debug("retrieving person resource with id: " + id);
		try {
			final Person person = personService.loadPerson(id);
			LOG.debug("person resource found " + person);
			return person;
		} catch (final PersonNotFoundException e) {
			LOG.error("no person resource found for " + id, e);
			// FIXME handle exception
		} 
		
		return null;
	}
	
	@RequestMapping(
			value="/person",
			method=RequestMethod.POST, 
			consumes={MediaType.APPLICATION_XML_VALUE}
	)
	@ResponseStatus(value=HttpStatus.OK)
	public void savePerson(final @RequestBody Person person) {
		LOG.debug("storing person " + person);
		personService.savePerson(person);
	}
}
