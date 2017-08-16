package com.onlineinteract;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static List<String> list = new ArrayList<>();

	public static void main(String[] args) {

		add("abc");
		add("def");
		add("ghi");

		printList();

		remove("def");

		printList();
		
		update(1, "zyx");
		
		printList();
	}

	public static void printList() {
		for (String item : list) {
			System.out.println(item);
		}
	}
	
	public static void add(String value) {
		list.add(value);
	}
	
	public static void remove(String value) {
		list.remove(value);
	}
	
	public static void update(int position, String value) {
		list.set(position, value);
	}

	public static List<String> getList() {
		return list;
	}
}