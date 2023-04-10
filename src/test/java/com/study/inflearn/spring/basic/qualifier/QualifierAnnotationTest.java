package com.study.inflearn.spring.basic.qualifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Qualifier 어노테이션은 @Component이외에 @Bean과 같이도 사용가능
public class QualifierAnnotationTest {

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
@ComponentScan(basePackages = "com.study.inflearn.spring.basic.qualifier")
class TestConfig {
}

@Component
class InjectionTarget {
	private Parent parent;
	
	//@Qualifier("main")지정시 -> 생성자의 파라메터 이름 parent는 무시됨
	//@Qualifier("main")어노테이션이 붙은 빈 탐색 -> 없으면 -> 빈 이름이 "main"인 빈 탐색 -> 없으면 에러
	public InjectionTarget(@Qualifier("main") Parent parent) {
		this.parent = parent;
	}
}

class Parent {
}

@Component("parent")
//@Qualifier("main")
class Child extends Parent {
}

@Component("childName2")
@Qualifier("alternative")
class Child2 extends Parent {
}