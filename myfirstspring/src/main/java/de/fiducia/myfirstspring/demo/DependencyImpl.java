package de.fiducia.myfirstspring.demo;

import org.springframework.stereotype.Component;

@Component
public class DependencyImpl implements Dependency {
	
	public DependencyImpl() {
		System.out.println("ctor dependency");
	}
	
	
	@Override
	public String foo(String message) {
		return message.toUpperCase();
	}

}
