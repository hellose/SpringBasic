package com.study.inflearn.spring.basic.primary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Autowired시 동일한 타입이 여러개일 때 -> 빈(@Component 또는 @Bean)에 @Primary 달린것이 주입됨
//@Qualifier의 단점 -> 주입 시켜주는 곳에서 @Qualifier 선언해줘야함. 또한 제2의 구분에 해당하는 이름도 관리해줘야한다. -> @Primary를 거의 사용
public class PrimaryAnnotationTest {

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
@ComponentScan(basePackages = "com.study.inflearn.spring.basic.primary")
class TestConfig {
}

@Component
class InjectionTarget {
	private Parent parent;

	public InjectionTarget(Parent parent) {
		this.parent = parent;
	}
}

class Parent {
}

@Component
@Primary
class Child extends Parent {
}

@Component
class Child2 extends Parent {
}