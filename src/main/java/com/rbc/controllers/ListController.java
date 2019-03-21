package com.rbc.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
		MediaType applicationJsonUtf8 = MediaType.APPLICATION_JSON_UTF8;
		String applicationJsonUtf8Value = MediaType.APPLICATION_JSON_UTF8_VALUE;
		return list.toString();
	}

	/**
	 * curl -i -H "Content-Type:application/json" http://localhost:8080/hashmap
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/hashmap")
	@ResponseBody
	public Map<String, String> getHashMap() {
		Map<String, String> hashmap = new HashMap<>();
		hashmap.put("name", "Gary");
		hashmap.put("DOB", "21/01/80");
		hashmap.put("Location", "Toronto");
		return hashmap;
	}
	
	/**
	 * curl -i -H "Content-Type:application/json" http://localhost:8080/listhashmap
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/listhashmap")
	@ResponseBody
	public List<Map<String, String>> getListHashMap() {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> hashmap = new HashMap<>();
		hashmap.put("name", "Gary");
		hashmap.put("DOB", "21/01/80");
		hashmap.put("Location", "Toronto");
		list.add(hashmap);
		
		hashmap = new HashMap<>();
		hashmap.put("name", "Jack");
		hashmap.put("DOB", "21/01/80");
		hashmap.put("Location", "Vancouver");
		list.add(hashmap);
		
		hashmap = new HashMap<>();
		hashmap.put("name", "Jane");
		hashmap.put("DOB", "21/01/80");
		hashmap.put("Location", "Calgary");
		list.add(hashmap);
		
		return list;
	}

	/**
	 * curl -i -H "Content-Type:application/json" -X POST --data '[{"DOB":"21/01/80","name":"Gary","Location":"Toronto"},{"DOB":"21/01/80","name":"Jack","Location":"Vancouver"},{"DOB":"21/01/80","name":"Jane","Location":"Calgary"}]' http://localhost:8080/listhashmap
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json", value = "/listhashmap")
	@ResponseBody
	public List<Map<String, String>> addHashMap(@RequestBody List<Map<String, String>> value) {
		System.out.println("do we get here?");
		return value;
	}
	
	/**
	 * curl -i -H "Content-Type:application/json" -X POST --data '{"DOB":"21/01/80","name":"Gary","Location":"Toronto"}' http://localhost:8080/hashmap
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json", value = "/hashmap")
	@ResponseBody
	public Map<String, String> addHashMap(@RequestBody Map<String, String> value) {
		System.out.println("do we get here?");
		return value;
	}
	
	/**
	 * Adds new value to array list
	 * 
	 * curl -i -H "Content-Type:text/plain" -X POST --data 'abc'
	 * http://localhost:8080/
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
	 * curl -i -H "Content-Type:text/plain" -X POST --data 'klm'
	 * http://localhost:8080/2
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
	 * curl -i -H "Content-Type:text/plain" -X DELETE --data 'abc'
	 * http://localhost:8080/
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