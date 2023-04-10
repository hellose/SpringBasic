package com.study.inflearn.spring.basic.discount;

import com.study.inflearn.spring.basic.member.Member;

public interface DiscountPolicy {

	int discount(Member member, int price);
}
