package com.study.inflearn.spring.basic.web.requestscope1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

//requestscope2 패키지 테스트를 위해 주석처리
//중요! - ObjectProvider.getObject()를 통해 다른 곳에서 각각 호출해도 같은 HTTP요청이면 동일한 빈이 반환된다.
//@Controller
@RequiredArgsConstructor
public class LogDemoController {

	private final LogDemoService logDemoService;
	private final ObjectProvider<MyLogger> myLoggerProvider;
	
	//@RequiredArgsConstructor사용으로 생성자 생성됨 -> 생성자가 하나일때 @Autowired 생략해도 자동 주입됨
	
	@RequestMapping("log-demo")
	@ResponseBody
//	public String logDemo(HttpServletRequest request) throws InterruptedException {
	public String logDemo(HttpServletRequest request) {
		MyLogger myLogger = myLoggerProvider.getObject();
		
		String requestUrl = request.getRequestURL().toString();
		
		//예제를 위해 Controller에 셋팅 -> requestURL을 MyLogger에 setting하는 것은 Controller보다 Filter나 Interceptor에 하는 것이 바람직
		myLogger.setRequestUrl(requestUrl);
		myLogger.log("controller test");
//		Thread.sleep(1000);
		logDemoService.logic("testId");
		return "OK";
	}

}
