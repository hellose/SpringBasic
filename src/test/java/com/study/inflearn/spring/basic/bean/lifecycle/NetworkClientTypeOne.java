package com.study.inflearn.spring.basic.bean.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;

//스프링 초창기에 나온 방법으로 요즘에는 거의 사용하지 않는다. 2003년도...

//단점 -> 스프링 프레임워크에 의존적임
//인터페이스를 구현할 수 없는 외부 라이브러리가 존재할 수 있다.

//빈 의존관계 주입 완료 후 셋팅할 동작이 있다면 -> InitializingBean 구현
//빈 소멸 전 셋팅할 동작이 있다면 -> DisposableBean 구현
@Getter
public class NetworkClientTypeOne implements InitializingBean, DisposableBean {
	private String url;

	public NetworkClientTypeOne() {
		System.out.println("=> call Constructor");
		System.out.println("생성자 호출, url = " + url);
//		connect();
	}

	public void setUrl(String url) {
		System.out.println("=> call Setter");
		this.url = url;
	}

	// 서비스 시작시 호출
	public void connect() {
		System.out.println("connect: " + url);
		System.out.println("초기화 연결중...");
	}

	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}

	// 서비스 종료시 호출
	public void disconnect() {
		System.out.println("close: " + url);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("=> call afterPropertiesSet");
		// 빈 생성시 생성자에 존재하던 작업을 이쪽에다 옮김
		connect();
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("=> call destroy");
		disconnect();
	}
}
