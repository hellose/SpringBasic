package com.study.inflearn.spring.basic.web.requestscope1;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;

//requestscope2 패키지 테스트를 위해 주석처리
//@Component
@Scope("request")
public class MyLogger {
	private String uuid;
	//requestUrl의 경우 빈 생성시 주입 받지 못하므로 setter를 통해 수동으로 셋팅
	private String requestUrl;

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public void log(String message) {
		System.out.println("[" + uuid + "]" + "[" + requestUrl + "] " + message);
	}

	@PostConstruct
	public void init() {
		uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: " + this);
	}
	
	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close: " + this);
	}
}
