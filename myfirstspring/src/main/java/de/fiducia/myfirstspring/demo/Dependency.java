package de.fiducia.myfirstspring.demo;

import org.springframework.stereotype.Component;

@Component
public class Dependency {
	
	public Dependency() {
		System.out.println("ctor dependency");
	}
	
	
	public String foo(String message) {
		return message.toUpperCase();
	}

}
