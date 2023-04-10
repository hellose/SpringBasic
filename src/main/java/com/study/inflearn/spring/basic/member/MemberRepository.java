package com.study.inflearn.spring.basic.member;

public interface MemberRepository {

	void save(Member member);

	Member findById(Long memberId);
}
