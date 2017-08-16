package com.rbc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * In-memory Array List Controller / API.
 * 
 * Time permitting, I would annotate with Swagger.
 * 
 * @author GBlack
 *
 */
@Controller
@EnableAutoConfiguration
public class ListController {

	/**
	 * Our in-memory ArrayList
	 */
	public static List<String> list = new ArrayList<>();

	/**
	 * Fetches and returns the array list
	 * 
	 * curl -i -H "Content-Type:text/plain" http://localhost:8080/
	 * 
	 * @return list
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/plain", value = "/")
	@ResponseBody
	public String getArrayList() {
		return list.toString();
	}

	/**
	 * Adds new value to array list
	 * 
	 * curl -i -H "Content-Type:text/plain" -X POST --data 'abc' http://localhost:8080/
	 * 
	 * @param value
	 * @return ResponseEntity
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain", value = "/")
	@ResponseBody
	public ResponseEntity<Object> addValue(@RequestBody String value) {
		list.add(value);
		return new ResponseEntity<>(value + " added to list", HttpStatus.OK);
	}
	
	/**
	 * Updates value on array list
	 * 
	 * curl -i -H "Content-Type:text/plain" -X POST --data 'klm' http://localhost:8080/2
	 * 
	 * @param position
	 * @param value
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "text/plain", produces = "text/plain", value = "/{position}")
	@ResponseBody
	public ResponseEntity<Object> updateValue(@PathVariable int position, @RequestBody String value) {
		list.set(position, value);
		return new ResponseEntity<>(value + " updated on list", HttpStatus.OK);
	}
	
	/**
	 * Removes value from array list
	 * 
	 * curl -i -H "Content-Type:text/plain" -X DELETE --data 'abc' http://localhost:8080/
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, consumes = "text/plain", produces = "text/plain", value = "/")
	@ResponseBody
	public ResponseEntity<Object> removeValue(@RequestBody String value) {
		list.remove(value);
		return new ResponseEntity<>(value + " removed from list", HttpStatus.OK);
	}

}