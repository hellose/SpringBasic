package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonTest {

	@Test
	@DisplayName("ApplicationContext에서 연속으로 조회했을 때 두 객체는 같은 객체이다")
	void findSingletonBeanTwice() {
		// 인자 -> 컴포넌트로 등록될 클래스 넘김
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

		SingletonBean bean1 = ac.getBean(SingletonBean.class);
		SingletonBean bean2 = ac.getBean(SingletonBean.class);

		System.out.println("bean1 = " + bean1);
		System.out.println("bean2 = " + bean2);

		assertThat(bean1).isSameAs(bean2);

		ac.close();

	}

	@Scope("singleton")
	static class SingletonBean {

		public SingletonBean() {
			System.out.println("SingletonBean.constructor");
		}

		@PostConstruct
		public void init() {
			System.out.println("SingletonBean.init");
		}

		@PreDestroy
		public void destroy() {
			System.out.println("SingletonBean.destroy");
		}
	}
}
