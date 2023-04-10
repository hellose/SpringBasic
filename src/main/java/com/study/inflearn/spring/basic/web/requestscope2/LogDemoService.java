package com.study.inflearn.spring.basic.web.requestscope2;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
	
	private final MyLogger myLogger;

	public void logic(String id) {
		//Service에서 MyLogger출력: class com.study.inflearn.spring.basic.web.requestscope2.MyLogger$$EnhancerBySpringCGLIB$$64a406a8
		System.out.println("Service에서 MyLogger출력: " + myLogger.getClass());
		myLogger.log("service id = " + id);
	}

}
