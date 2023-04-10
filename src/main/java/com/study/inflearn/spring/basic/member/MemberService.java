package com.study.inflearn.spring.basic.member;

public interface MemberService {
	void join(Member member);

	Member findMember(Long memberId);
}
