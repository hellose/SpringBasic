package com.study.inflearn.spring.basic.discount;

import com.study.inflearn.spring.basic.member.Grade;
import com.study.inflearn.spring.basic.member.Member;

public class FixedDiscountPolicy implements DiscountPolicy {

	private int discountFixAmmount = 1000;

	@Override
	public int discount(Member member, int price) {
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmmount;
		} else {
			return 0;
		}
	}

}
