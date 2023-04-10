package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

//JSR-330 Provider <- javax.inject.Provider 인터페이스
//이방법을 사용하려면 'javax.inject:javax.inject:1'라이브러리를 gradle에 추가해야한다(스프링부트3.0미만 해당)
class SingletonWithPrototypeTest5 {

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
		private Provider<PrototypeBean> prototypeBeanProvider;

		@Autowired
		public ClientBean() {
			System.out.println("ClientBean.constructor");
		}

		public int logic() {
			System.out.println("ClientBean.logic");
			//Provider.get();
			PrototypeBean prototypeBean = prototypeBeanProvider.get();
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
