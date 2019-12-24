package com.m3bi.hotelbooking.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.SpringBootTest;

import com.m3bi.hotelbooking.application.HotelBookingApplication;
import com.m3bi.hotelbooking.controller.LoginController;
import com.m3bi.hotelbooking.model.CustomResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=LoginController.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = HotelBookingApplication.class)
public class LoginTest {
	
	    private TestRestTemplate restTemplate;
	    private URL base;
	    @LocalServerPort
	    int randomServerPort;
	    
	    @Before
	    public void setUp() throws MalformedURLException {
	    	restTemplate = new TestRestTemplate("admin@admin.com", "admin");
	    	base = new URL("http://localhost:"+randomServerPort+"/login");
	    }
	
	    @Test
	    public void callLogin()throws IllegalStateException, IOException {
	    	ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
	    	assertEquals(HttpStatus.OK, response.getStatusCode());
	    }
	
	    @Test
	    public void callLoginWithWrongCred() throws Exception {
	    	restTemplate = new TestRestTemplate("admin@admin.com", "admin1");
	    	ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);
	    	assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	    	assertTrue(HttpStatus.UNAUTHORIZED.equals(response.getStatusCode()));
	    }

}
