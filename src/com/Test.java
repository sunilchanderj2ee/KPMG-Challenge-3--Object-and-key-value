package com;

import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class Test {

	public static void main(String[] args) {

		// create nested json object java string to Json object step1
		String str = "{ \"a\":{\"b\":{\"c\":\"KPMG challenge-3 has been done successfully \"}}}";
		System.out.printf("Json String %s", str);
		JSONObject strJsonObject = new JSONObject(str);
		
while(true) {
		// receive input from the System e.g. from user input step2
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a object key: ");// a\b\c
		String key = sc.nextLine();
		// %s is for string argument %n for new line
		
if(key.equals("exit")) {
	
	System.out.printf(" Thank you very much.");
	return;}
System.out.printf("Key is :%s%n ", key);

		// static function and pass json object and key Step 3
		String response = getObjectValue(strJsonObject, key);
		System.out.printf("value for key %s  is  %s%n", key, response );
}
	}

	static String getObjectValue(JSONObject object, String key) {

		if (object == null || key == null || key.isEmpty()) {
			return null;
		} else {

			try {
				// For one \ in string we have to use \\ in split()
				String[] keyArr = key.split("\\\\");
				int keyArrSize = keyArr.length;
				for (int index = 0; index < keyArrSize; index++) {
					String indexKey = keyArr[index];
					System.out.println("indexKey " +  indexKey);
					Object nestedJsonObject = getValue(object, indexKey);
					if (nestedJsonObject == null) {
						return null;
					} else if (index + 1 == keyArrSize) {
						return nestedJsonObject.toString();
					} else {
						object = (JSONObject) nestedJsonObject;
					}

				}
			} catch (Exception exc) {
				System.out.printf(exc.getMessage());
			}
			return null;
		}
		

	}

	static Object getValue(JSONObject objects, String key) {
		try {
			return objects.get(key);
		} catch (JSONException e) {
			return null;
		}

	}

}