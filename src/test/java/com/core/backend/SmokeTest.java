package com.core.backend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.core.backend.controller.TestController;

@SpringBootTest
class SmokeTest {

	@Autowired
	private TestController testController;

	@Test
	void contextLoads() throws Exception {
		assertThat(testController).isNotNull();
	}
	
}
