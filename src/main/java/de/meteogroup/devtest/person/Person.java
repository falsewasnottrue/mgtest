package de.meteogroup.devtest.person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for Person resources.
 * 
 * In a real system the attributes would have more specific types (e.g. Long id, joda.DateTime dateOfBirth etc),
 * but in this test system Strings are used to prevent conversion issues etc.
 * 
 * @author kuschel
 */
@XmlRootElement(name="person")
public class Person {

	private String id;

	private String familyName;
	private String givenName;
	private String middleNames;
	
	private Date dateOfBirth;
	private Date dateOfDeath;
	
	private String placeOfBirth;
	private Float height;
	private String twitterId;

	// format of dates in the person data files
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public Person() {}
	
	public Person(final Properties personData) {
		// TODO should use attribute name constants or enum
		id = personData.getProperty("id");
		familyName = personData.getProperty("familyName");
		givenName = personData.getProperty("givenName");
		middleNames = personData.getProperty("middleNames");
		placeOfBirth = personData.getProperty("placeOfBirth");
		twitterId = personData.getProperty("twitterId");
		
		dateOfBirth = convertDate(personData.getProperty("dateOfBirth"));
		dateOfDeath = convertDate(personData.getProperty("dateOfDeath"));
		
		height = convertFloat(personData.getProperty("height"));
	}
	
	private Date convertDate(final String value) {
		if (value == null || "".equals(value)) { return null; }
		try {
			return DATE_FORMAT.parse(value);
		} catch (final ParseException e) {
			throw new IllegalStateException("Unexpected format of person date: " + value);
		}
	}
	
	private Float convertFloat(final String value) {
		if (value == null || "".equals(value)) { return null; }
		return Float.valueOf(value);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getMiddleNames() {
		return middleNames;
	}
	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + " (" + familyName + ", " + givenName + ")";
	}
}
