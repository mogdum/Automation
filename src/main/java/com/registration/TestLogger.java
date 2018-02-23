package com.registration;

import org.apache.log4j.Logger;

public class TestLogger {
	static Logger log = Logger.getLogger(TestLogger.class.getName());
	public static void main(String[] args) {
		
	
		log.debug("debug message");
		log.info("info message");
		log.warn("warn message");
		log.error("error message");
		log.fatal("fatal message");

	}

}
