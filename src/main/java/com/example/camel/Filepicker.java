package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Filepicker {

	public static void main(String[] args) throws Exception {

		@SuppressWarnings("resource")
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
//
//				from("file:/Users/Promantus/eclipse-workspace/camel-file-picker/target/input").log("${body}")
//						.to("file:/Users/Promantus/eclipse-workspace/camel-file-picker/target/output");
				
				from("file:D:/Files_to_process?delay=10000")
				.process(new MyFileProcessor())
						.to("direct:processFile");

				from("direct:processFile").log("Processing File: ${header.FileName}")
				.to("file:D:/Processed_files");
			}
		});
		camelContext.start();
		Thread.sleep(60000);
		camelContext.stop();
	}

}
