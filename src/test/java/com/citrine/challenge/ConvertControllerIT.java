package com.citrine.challenge;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConvertControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
    	
        this.base = new URL("http://localhost:" + port + "/units/si?units=");
    }

    @Test
    public void testNameCase() throws Exception {
    	
    	String url = base.toString() + "degree/minute";
    	UnitInfo expected = new UnitInfo("rad/s", "0.00029088820866572", Converter.MATH_CONTEXT);
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getBody(), equalTo(expected));
    }
    
    @Test
    public void testSymbolCase() throws Exception {
    	
    	String url = base.toString() + "ha*°";
    	UnitInfo expected = new UnitInfo("m2*rad", "174.53292519943", Converter.MATH_CONTEXT);
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getBody(), equalTo(expected));
    }
    
    @Test
    public void testParenCase() throws Exception {
    	
    	String url = base.toString() + "(degree/(minute*hectare))";
    	UnitInfo expected = new UnitInfo("(rad/(s*m2))", "2.9088820866572E-8", Converter.MATH_CONTEXT);
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getBody(), equalTo(expected));
    }
    
    @Test
    public void testMissingParenCase() throws Exception {
    	
    	String url = base.toString() + "(degree/(minute*hectare)";
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }

    @Test
    public void testMismatchedParenCase() throws Exception {
    	
    	String url = base.toString() + "(degree))/(minute*hectare))";
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }

    public void testBadNameCase() throws Exception {
    	
    	String url = base.toString() + "degre/minute";
        ResponseEntity<UnitInfo> response = template.getForEntity(url, UnitInfo.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
    }
}
