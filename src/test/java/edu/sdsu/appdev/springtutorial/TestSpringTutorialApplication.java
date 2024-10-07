package edu.sdsu.appdev.springtutorial;

import org.springframework.boot.SpringApplication;

public class TestSpringTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringTutorialApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
