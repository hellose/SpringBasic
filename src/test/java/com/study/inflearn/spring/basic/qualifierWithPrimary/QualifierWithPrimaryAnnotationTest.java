package com.study.inflearn.spring.basic.qualifierWithPrimary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Qualifier와 @Primary 동시 적용시 -> 상세한 것인 @Qualifier가 우선권을 가짐
public class QualifierWithPrimaryAnnotationTest {

	@Test
	@DisplayName("의존성 주입 에러 발생 O")
	void exceptionOccur() {
		Assertions.assertThrows(Exception.class, () -> new AnnotationConfigApplicationContext(TestConfig.class));
	}

	@Test
	@DisplayName("의존성 주입 에러 발생 X")
	void exceptionNotOccur() {
		Assertions.assertDoesNotThrow(() -> new AnnotationConfigApplicationContext(TestConfig.class));
	}
}

@Configuration
@ComponentScan(basePackages = "com.study.inflearn.spring.basic.qualifierWithPrimary")
class TestConfig {
}

@Component
class InjectionTarget {
	private Parent parent;

	public InjectionTarget(@Qualifier("main") Parent parent) {
		this.parent = parent;
	}
}

class Parent {
}

@Component
@Qualifier("main")
class Child extends Parent {
}

@Component
@Primary
@Qualifier("alternative")
class Child2 extends Parent {
}