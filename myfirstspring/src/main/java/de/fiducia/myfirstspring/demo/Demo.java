package de.fiducia.myfirstspring.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Lazy
public class Demo {
	
	
	private final Dependency dependency;
	
	@Autowired
	public Demo(final Dependency dependency) {
		this.dependency =dependency;
		System.out.println(dependency.foo("Ctor Demo"));
	}
	

	@PostConstruct
	public void init() {
		System.out.println(dependency.foo("Post Contruct"));
	}
	
	@PreDestroy
	public void dispose() {
		System.out.println(dependency.foo("..und tschüss..."));
	}
}
