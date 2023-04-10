package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonWithPrototypeTest3 {

	@Test
	@DisplayName("singleton 빈이 prototypebean을 사용할 때마다 ApplicationContext에서 조회하여 새 객체를 제공받는다.(무식한 방법으로 사용x)")
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

//		private final PrototypeBean prototypeBean;

		// ApplicationContext를 주입받으면 -> 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다.
		@Autowired
		AnnotationConfigApplicationContext ac;

//		@Autowired
//		public ClientBean(PrototypeBean prototypeBean) {
//			this.prototypeBean = prototypeBean;
//			System.out.println("ClientBean.constructor");
//		}

		public int logic() {
			System.out.println("ClientBean.logic");
			PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
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
