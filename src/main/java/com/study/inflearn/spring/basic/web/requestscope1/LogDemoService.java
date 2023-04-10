package com.study.inflearn.spring.basic.web.requestscope1;

import org.springframework.beans.factory.ObjectProvider;

import lombok.RequiredArgsConstructor;

//requestscope2 패키지 테스트를 위해 주석처리
//@Service
@RequiredArgsConstructor
public class LogDemoService {

	private final ObjectProvider<MyLogger> myLoggerProvider;

	public void logic(String id) {
		MyLogger myLogger = myLoggerProvider.getObject();
		myLogger.log("service id = " + id);
	}

}
