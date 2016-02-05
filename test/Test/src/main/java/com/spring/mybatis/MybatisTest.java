package com.spring.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mybatis.dao.AccountDao;
import com.spring.mybatis.pojo.Account;

public class MybatisTest {
	
	private static String configLocation ="classpath:com/spring/mybatis/spring-mybatis.xml";

	public static void main(String[] args) {
		ApplicationContext ctx= new ClassPathXmlApplicationContext(configLocation);
		AccountDao dao = ctx.getBean(AccountDao.class);
		List<Account> list = dao.queryAccountList(null);
		System.out.println(list.size());
	}

}
