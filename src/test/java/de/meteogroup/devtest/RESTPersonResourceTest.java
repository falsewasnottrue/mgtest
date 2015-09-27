package de.meteogroup.devtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

/**
 * Rudimentary tests for requests for Person resources.
 * 
 * @author kuschel
 */
public class RESTPersonResourceTest {

	/**
	 * Load resource as JSON
	 */
	@Test
	public void shouldRetrieveJSONPersonResource() throws Exception {
		URL url = new URL("http://localhost:8080/devtest/myservice/person/1");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder result = new StringBuilder();

		while ((inputLine = in.readLine()) != null) { 
			result.append(inputLine);
			System.out.println(inputLine);
		}
		in.close();
		
		assertTrue(result.toString().contains("\"familyName\":\"Rooney\""));
	}
	
	/**
	 * Load resource as XML
	 */
	@Test
	public void shouldRetrieveXMLPersonResource() throws Exception {
		URL url = new URL("http://localhost:8080/devtest/myservice/person/1");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");
		
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder result = new StringBuilder();

		while ((inputLine = in.readLine()) != null) { 
			result.append(inputLine);
			System.out.println(inputLine);
		}
		in.close();
		
		assertTrue(result.toString().contains("<familyName>Rooney</familyName>"));
	}
	
	/**
	 * Store resource, than load it again as json
	 */
	@Test
	public void shouldAcceptXMLPostPersonResource() throws Exception {
		URL url = new URL("http://localhost:8080/devtest/myservice/person");
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><person><dateOfBirth>1976-01-11</dateOfBirth><familyName>Kuschel</familyName><givenName>Rasmus</givenName><height>1.76</height><id>23</id><middleNames></middleNames><placeOfBirth>Ludwigshafen</placeOfBirth><twitterId></twitterId></person>";
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/xml");
		conn.setDoOutput(true);
		
		conn.setRequestProperty("Content-Length", ""+ body.length());
		 
        // Create I/O streams
		DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
 
        // Send request
        outStream.writeBytes(body);
        outStream.flush();
        outStream.close();
        
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		
		// stored, now load it again as json
		
		url = new URL("http://localhost:8080/devtest/myservice/person/23");
		conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		assertEquals(HttpURLConnection.HTTP_OK, conn.getResponseCode());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder result = new StringBuilder();

		while ((inputLine = in.readLine()) != null) { 
			result.append(inputLine);
			System.out.println(inputLine);
		}
		in.close();
		
		assertTrue(result.toString().contains("\"familyName\":\"Kuschel\""));
	}
}
