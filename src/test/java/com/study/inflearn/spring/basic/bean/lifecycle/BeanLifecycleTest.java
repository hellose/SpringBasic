package com.study.inflearn.spring.basic.bean.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//스프링 빈의 라이크사이클
//스프링 컨테이너 생성 -> 스프링 빈 생성(+생성자 의존관계 주입) -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
public class BeanLifecycleTest {
	@Test
	@DisplayName("Bean Lifecycle Test")
	void test() {
		ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		ac.close();

	}
}

@Configuration
class TestConfig {

//	@Bean
	public NetworkClientTypeOne networkClientTypeOne() {
		NetworkClientTypeOne client = new NetworkClientTypeOne();
		client.setUrl("http://test.com");
		return client;
	}

//	@Bean(initMethod = "init", destroyMethod = "close")
	public NetworkClientTypeTwo networkClientTypeTwo() {
		NetworkClientTypeTwo client = new NetworkClientTypeTwo();
		client.setUrl("http://test.com");
		return client;
	}

	@Bean
	public NetworkClientTypeThree networkClientTypeThree() {
		NetworkClientTypeThree client = new NetworkClientTypeThree();
		client.setUrl("http://test.com");
		return client;
	}
}