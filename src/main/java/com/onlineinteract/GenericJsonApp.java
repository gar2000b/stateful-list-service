package com.onlineinteract;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericJsonApp {
	public static void main(String[] args) {
		ObjectMapper om = new ObjectMapper();
		String json = "{\"a\":\"a\",\"b\":\"b\",\"c\":[{\"d\":\"d\"},{\"e\":\"e\"}],\"f\":{\"g\":\"g\"},\"h\":[1,2,3],\"i\":4.3,\"j\":7,\"k\":true}";
		try {
			Map<String, Object> map = om.readValue(json, new TypeReference<Map<String, Object>>() {
			});
			processMap(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private static void processMap(Map<String, Object> map) {
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
				System.out.println(entry.getKey() + " is a String");
			} else {
				System.out.println(entry.getKey() + " is not a String");
			}
			if (entry.getValue() instanceof Integer) {
				System.out.println(entry.getKey() + " is an Integer");
			} else {
				System.out.println(entry.getKey() + " is not an Integer");
			}
			if (entry.getValue() instanceof Double) {
				System.out.println(entry.getKey() + " is a Double");
			} else {
				System.out.println(entry.getKey() + " is not a Double");
			}
			if (entry.getValue() instanceof Boolean) {
				System.out.println(entry.getKey() + " is a Boolean");
			} else {
				System.out.println(entry.getKey() + " is not a Boolean");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static void processList(List<Object> list) {
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
				System.out.println(object + " is a String");
			} else {
				System.out.println("list entry is not a String");
			}
			if (object instanceof Integer) {
				System.out.println(object + " is an Integer");
			} else {
				System.out.println("list entry is not an Integer");
			}
			if (object instanceof Double) {
				System.out.println(object + " is a Double");
			} else {
				System.out.println("list entry is not a Double");
			}
			if (object instanceof Boolean) {
				System.out.println(object + " is a Boolean");
			} else {
				System.out.println("list entry is not a Boolean");
			}
		}
	}
}
