package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

//ObjectFactory, ObjectProvider -> 스프링에 의존적임
class SingletonWithPrototypeTest4 {

	@Test
	@DisplayName("ObjectProvider를 주입받아 ObjectProvider에게 prototype 빈 요청")
	void singletonClientUsePrototypeBean() {
		// 인자 -> 컴포넌트로 등록될 클래스 넘김
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,
				PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean1.logic();
		assertThat(count2).isEqualTo(1);

		ac.close();
	}

	@Scope("singleton")
	static class ClientBean {

		@Autowired
		private ObjectProvider<PrototypeBean> prototypeBeanProvider;

		@Autowired
		public ClientBean() {
			System.out.println("ClientBean.constructor");
		}

		public int logic() {
			System.out.println("ClientBean.logic");
			//ObjectProvider의 상위 인터페이스인 ObjectFactory의 getObject 메서드
			//ObjectProvider에는 추가적인 기능이 몇개 더있음
			//Application.getBean의 대리자 역할정도로 보면되겠다.
			PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
			prototypeBean.addCount();
			return prototypeBean.getCount();
		}

		@PostConstruct
		public void init() {
			System.out.println("ClientBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("ClientBean.destroy");
		}
	}

	@Scope("prototype")
	static class PrototypeBean {

		private int count = 0;

		public void addCount() {
			count++;
		}

		public int getCount() {
			return count;
		}

		public PrototypeBean() {
			System.out.println("PrototypeBean.constructor");
		}

		@PostConstruct
		public void init() {
			System.out.println("PrototypeBean.init " + this);
		}

		@PreDestroy
		public void destroy() {
			System.out.println("PrototypeBean.destroy");
		}
	}

}
