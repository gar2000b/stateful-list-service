package com.onlineinteract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericJsonApp {
	List<Map<String, Object>> flattenedPayloadValues;

	public GenericJsonApp() {
		flattenedPayloadValues = new ArrayList<>();
		ObjectMapper om = new ObjectMapper();
		String json = "{\"a\":\"a\",\"b\":\"b\",\"c\":[{\"d\":\"d\"},{\"e\":\"e\"}],\"f\":{\"g\":\"g\"},\"h\":[1,2,3],\"i\":4.3,\"j\":7,\"k\":true}";
		try {
			Map<String, Object> map = om.readValue(json, new TypeReference<Map<String, Object>>() {
			});
			processMap(map);
			System.out.println("\nFlattened values:\n");
			processFlattenedValues();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processFlattenedValues() {
		for (Map<String, Object> map : flattenedPayloadValues) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}
	}

	public static void main(String[] args) {
		new GenericJsonApp();
	}

	@SuppressWarnings("unchecked")
	private void processMap(Map<String, Object> map) {
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println("\n" + entry.getKey() + ": " + entry.getValue());
			if (entry.getValue() instanceof Map) {
				System.out.println(entry.getKey() + " is a Map");
				processMap((Map<String, Object>) entry.getValue());
			} else {
				System.out.println(entry.getKey() + " is not a Map");
			}
			if (entry.getValue() instanceof List) {
				System.out.println(entry.getKey() + " is a List");
				processList((List<Object>) entry.getValue());
			} else {
				System.out.println(entry.getKey() + " is not a List");
			}
			if (entry.getValue() instanceof String) {
				injectMapEntryToFlattenedPayloadValues(entry);
				System.out.println(entry.getKey() + " is a String");
			} else {
				System.out.println(entry.getKey() + " is not a String");
			}
			if (entry.getValue() instanceof Integer) {
				injectMapEntryToFlattenedPayloadValues(entry);
				System.out.println(entry.getKey() + " is an Integer");
			} else {
				System.out.println(entry.getKey() + " is not an Integer");
			}
			if (entry.getValue() instanceof Double) {
				injectMapEntryToFlattenedPayloadValues(entry);
				System.out.println(entry.getKey() + " is a Double");
			} else {
				System.out.println(entry.getKey() + " is not a Double");
			}
			if (entry.getValue() instanceof Boolean) {
				injectMapEntryToFlattenedPayloadValues(entry);
				System.out.println(entry.getKey() + " is a Boolean");
			} else {
				System.out.println(entry.getKey() + " is not a Boolean");
			}
		}
	}

	private void injectMapEntryToFlattenedPayloadValues(Map.Entry<String, Object> entry) {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put(entry.getKey(), entry.getValue());
		flattenedPayloadValues.add(hashMap);
	}

	@SuppressWarnings("unchecked")
	private void processList(List<Object> list) {
		for (Object object : list) {
			if (object instanceof Map) {
				System.out.println("list entry is a Map");
				processMap((Map<String, Object>) object);
			} else {
				System.out.println("list entry is not a Map");
			}
			if (object instanceof List) {
				System.out.println("list entry is a List");
				processList((List<Object>) object);
			} else {
				System.out.println("list entry is not a List");
			}
			if (object instanceof String) {
				injectListEntryToFlattenedPayloadValues(object);
				System.out.println(object + " is a String");
			} else {
				System.out.println("list entry is not a String");
			}
			if (object instanceof Integer) {
				injectListEntryToFlattenedPayloadValues(object);
				System.out.println(object + " is an Integer");
			} else {
				System.out.println("list entry is not an Integer");
			}
			if (object instanceof Double) {
				injectListEntryToFlattenedPayloadValues(object);
				System.out.println(object + " is a Double");
			} else {
				System.out.println("list entry is not a Double");
			}
			if (object instanceof Boolean) {
				injectListEntryToFlattenedPayloadValues(object);
				System.out.println(object + " is a Boolean");
			} else {
				System.out.println("list entry is not a Boolean");
			}
		}
	}

	private void injectListEntryToFlattenedPayloadValues(Object object) {
		Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("collection", object);
		flattenedPayloadValues.add(hashMap);
	}
}
