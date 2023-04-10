package com.study.inflearn.spring.basic.bean.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeTest2 {

	@Test
	@DisplayName("ApplicationContext에서 스코프가 prototype인 빈을 조회할때마다 새로운 빈을 생성해준다.")
	void findPrototypeBeanTwice() {

		// 인자 -> 컴포넌트로 등록될 클래스 넘김
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

		System.out.println("find prototypeBean");
		PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
		bean1.addCount();
		assertThat(bean1.getCount()).isEqualTo(1);

		System.out.println("find prototypeBean");
		PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
		bean2.addCount();
		assertThat(bean1.getCount()).isEqualTo(1);

		// 해제가 필요할 때 ac.close 전에 직접 소멸 메서드 호출해줘야함
		bean1.destroy();
		bean2.destroy();

		ac.close();
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
