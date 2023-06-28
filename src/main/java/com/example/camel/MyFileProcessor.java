package com.example.camel;

import java.nio.charset.StandardCharsets;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;

public class MyFileProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		byte[] fileData = exchange.getIn().getBody(byte[].class);
		String fileContent = new String(fileData, StandardCharsets.UTF_8);

		String contentFormatter = "";
		if (isJsonFile(fileContent)) {
			contentFormatter = fileContent;
		} else if (isXmlFile(fileContent)) {
			JSONObject jsonObject = XML.toJSONObject(fileContent);
			contentFormatter = jsonObject.toString(4);
			System.out.println("Coverted to Json");
			System.out.println(contentFormatter);
		} else {
			System.out.println("File content");
			System.out.println(fileContent);
		}
	}

	private boolean isXmlFile(String fileContent) {
		try {
			new JSONObject(fileContent);
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	private boolean isJsonFile(String fileContent) {
		try {
			new JSONObject(fileContent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
