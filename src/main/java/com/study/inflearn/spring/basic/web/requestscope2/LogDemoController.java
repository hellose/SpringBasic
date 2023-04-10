package com.study.inflearn.spring.basic.web.requestscope2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	//1. 스프링에서 자체적으로 바이트 코드 조작한 MyLogger를 상속한 클래스 인스턴스를 최초 스프링 컨테이너 생성시 주입시킴
	//2. 해당 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 찾아오는 로직이 들어가있음 
	//또한 ApplicationContext.getBean시에도 프록시 객체가 조회됨
	private final MyLogger myLogger;
	
	@RequestMapping("log-demo")
	@ResponseBody
	public String logDemo(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		
		//Contoller에서 MyLogger출력: class com.study.inflearn.spring.basic.web.requestscope2.MyLogger$$EnhancerBySpringCGLIB$$64a406a8
		System.out.println("Contoller에서 MyLogger출력: " + myLogger.getClass());
		
		myLogger.setRequestUrl(requestUrl);
		myLogger.log("controller test");
		logDemoService.logic("testId");
		return "OK";
	}

}
