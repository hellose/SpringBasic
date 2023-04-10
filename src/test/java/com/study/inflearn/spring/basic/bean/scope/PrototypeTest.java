package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeTest {

	@Test
	@DisplayName("ApplicationContext에서 연속으로 조회했을 때 두 객체는 다른 객체이다")
	void findPrototypeBeanTwice() {
		// 인자 -> 컴포넌트로 등록될 클래스 넘김
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		System.out.println("find prototypeBean");
		PrototypeBean bean1 = ac.getBean(PrototypeBean.class);

		System.out.println("find prototypeBean");
		PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

		System.out.println("bean1 = " + bean1);
		System.out.println("bean2 = " + bean2);

		assertThat(bean1).isNotSameAs(bean2);

		// 해제가 필요할 때 ac.close 전에 직접 소멸 메서드 호출해줘야함
		bean1.destroy();
		bean2.destroy();

		ac.close();

//		find prototypeBean
//		PrototypeBean.constructor
//		PrototypeBean.init
//		find prototypeBean
//		PrototypeBean.constructor
//		PrototypeBean.init
//		bean1 = com.study.inflearn.spring.basic.bean.scope.PrototypeTest$PrototypeBean@49c66ade
//		bean2 = com.study.inflearn.spring.basic.bean.scope.PrototypeTest$PrototypeBean@6239aba6

	}

	@Scope("prototype")
	static class PrototypeBean {

		public PrototypeBean() {
			System.out.println("PrototypeBean.constructor");
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init");
		}

		// PostConstruct까지는 해줌

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}
}
