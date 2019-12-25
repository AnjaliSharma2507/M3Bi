package com.m3bi.hotelbooking.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m3bi.hotelbooking.application.HotelBookingApplication;
import com.m3bi.hotelbooking.controller.BookingController;
import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.model.CustomResponse;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.utility.DateTime;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=BookingController.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = HotelBookingApplication.class)
public class BookingTest {
	private TestRestTemplate restTemplate;
    private URL base;
    @LocalServerPort
    int randomServerPort;
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Before
    public void setUp() throws MalformedURLException {
    	restTemplate = new TestRestTemplate("test@gmail.com", "test");
    	base = new URL("http://localhost:"+randomServerPort);
    }
    
    @Test
    public void createBooking() throws IllegalStateException, IOException{
    	RequestRoomDetails bookingDetails = new RequestRoomDetails();
    	bookingDetails.setUseBonus(true);
    	bookingDetails.setCheckInDate(DateTime.getCurrentDate());
    	bookingDetails.setCheckOutDate(DateTime.getDateAfterNumberDays(2));
    	bookingDetails.setRoom((long) 1);
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(objectMapper.writeValueAsString(bookingDetails));
    	HttpEntity<String> request = 
    		      new HttpEntity<String>(objectMapper.writeValueAsString(bookingDetails), headers);
    	ResponseEntity<String> bookingResponse = restTemplate.postForEntity(base.toString()+"/booking", request, String.class);
    	assertEquals(HttpStatus.OK, bookingResponse.getStatusCode());
    	CustomResponse<BookingDetails> response = objectMapper.readValue(bookingResponse.getBody(), new TypeReference<CustomResponse<BookingDetails>>(){});
    	assertTrue(response.getIsSuccess());
    }
}
