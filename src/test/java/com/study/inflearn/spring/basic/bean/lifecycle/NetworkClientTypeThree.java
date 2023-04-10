package com.study.inflearn.spring.basic.bean.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.Getter;

//@PostConstruct
//@PreDestroy
// -> 스프링에 종속적인 기술이 아니라 JSR-250 자바플랫폼 공통 어노테이션임 -> 스프링이 아닌 다른 컨테이너에서도 동작함

//유일한 단점 -> 코드를 고칠 수 없는 외부 라이브러리를 초기화,종료해야하면 두번째 방식인 TypeTwo 방식 사용해야함
@Getter
public class NetworkClientTypeThree {
	private String url;

	public NetworkClientTypeThree() {
		System.out.println("=> call constructor");
		System.out.println("생성자 호출, url = " + url);
//		connect();
	}

	public void setUrl(String url) {
		System.out.println("=> call setter");
		this.url = url;
	}

	public void connect() {
		System.out.println("connect: " + url);
		System.out.println("초기화 연결중...");
	}

	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}

	public void disconnect() {
		System.out.println("close: " + url);
	}

	// Annotation 추가
	@PostConstruct
	public void init() {
		System.out.println("=> call init");
		connect();
	}

	// Annotation 추가
	@PreDestroy
	public void close() {
		System.out.println("=> call close");
		disconnect();
	}
}
