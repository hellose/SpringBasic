package com.study.inflearn.spring.basic.autowired;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class AutowiredSameTypeBeanConflictTest {

	@Test
	@DisplayName("의존성 주입 에러O")
	void exceptionOccur() {
		Assertions.assertThrows(Exception.class, () -> new AnnotationConfigApplicationContext(TestConfig.class));
	}

	@Test
	@DisplayName("의존성 주입 에러X")
	void exceptionNotOccur() {
		Assertions.assertDoesNotThrow(() -> new AnnotationConfigApplicationContext(TestConfig.class));
	}
}

@Configuration
@ComponentScan(basePackages = "com.study.inflearn.spring.basic.autowired.conflict")
class TestConfig {
}

@Component
class InjectionTarget {

	// 필드 주입시 동일 타입 존재 -> 필드명에 해당하는 빈으로 주입 -> 빈 이름 "child"인 빈 주입
	@Autowired
	private Parent child;

	private Child c;

	// 생성자 주입시 동일 타입 존재 -> 파라메터명에 해당하는 빈으로 주입 -> 빈 이름 "grandChild1"인 빈 주입
	@Autowired
	public InjectionTarget(Child grandChild1) {
		this.c = grandChild1;
	}
}

class Parent {
}

@Component
class Child extends Parent {
}

@Component
class Child2 extends Parent {
}

@Component
class GrandChild1 extends Child {
}

@Component
class GrandChild2 extends Child {
}
