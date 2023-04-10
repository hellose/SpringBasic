package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonWithPrototypeTest2 {

	@Test
	@DisplayName("싱글톤 빈에 prototype 빈을 주입한 뒤 싱글톤 빈을 연속으로 조회하면 prototype빈은 다시 생성해주지 않는다.")
	void singletonClientUsePrototypeBean() {
		// 인자 -> 컴포넌트로 등록될 클래스 넘김
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

		ClientBean clientBean1 = ac.getBean(ClientBean.class);
		int count1 = clientBean1.logic();
		assertThat(count1).isEqualTo(1);

		ClientBean clientBean2 = ac.getBean(ClientBean.class);
		int count2 = clientBean1.logic();
		assertThat(count2).isEqualTo(2);

		ac.close();
	}

	@Scope("singleton")
	static class ClientBean {

		private final PrototypeBean prototypeBean;

		@Autowired
		public ClientBean(PrototypeBean prototypeBean) {
			this.prototypeBean = prototypeBean;
			System.out.println("ClientBean.constructor");
		}

		public int logic() {
			System.out.println("ClientBean.logic");
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
