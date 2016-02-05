package com.spring.aop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.AopUtils;

public class AopAdvisor {

	
	public static void main(String[] args) {
		List<Advisor> candidateAdvisors = new ArrayList<Advisor>();
		
		List<Advisor> list = AopUtils.findAdvisorsThatCanApply(candidateAdvisors, HelloServiceImpl.class);
		
		System.out.println(candidateAdvisors);
		System.out.println(list);
	}
}
