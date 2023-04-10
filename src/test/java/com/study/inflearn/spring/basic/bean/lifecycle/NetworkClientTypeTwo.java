package com.study.inflearn.spring.basic.bean.lifecycle;

import lombok.Getter;

//메서드 이름 자유롭게 설정 가능
//스프링 빈 클래스가 스프링 코드에 의존하지 않는다
//외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
@Getter
public class NetworkClientTypeTwo {
	private String url;

	public NetworkClientTypeTwo() {
		System.out.println("=> call constructor");
		System.out.println("생성자 호출, url = " + url);
//		connect();
	}

	public void setUrl(String url) {
		System.out.println("=> call setter");
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

	public void init()  {
		System.out.println("=> call init");
		connect();
	}

	public void close() {
		System.out.println("=> call close");
		disconnect();
	}
}
