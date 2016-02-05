package com.spring.mybatis.dao;

import java.util.List;

import com.spring.mybatis.pojo.Account;

public interface AccountDao {
	
	public List<Account> queryAccountList(String account);

}
