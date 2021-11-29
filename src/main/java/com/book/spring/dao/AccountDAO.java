package com.book.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.book.spring.entity.Account;

@Transactional
@Repository
public class AccountDAO {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Account findAccount(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(Account.class, userName);
	}

	public void registerAccount(Account account) {
		Account accountAdd = new Account();
		accountAdd.setUserName(account.getUserName());
		accountAdd.setEmail(account.getEmail());
		accountAdd.setConfirmpassword(passwordEncoder.encode(account.getEncrytedPassword()));
		accountAdd.setFirstname(account.getFirstname());
		accountAdd.setActive(true);
		accountAdd.setEncrytedPassword(passwordEncoder.encode(account.getEncrytedPassword()));
		accountAdd.setLastname("");
		accountAdd.setUserRole("ROLE_EMPLOYEE");
		this.sessionFactory.getCurrentSession().persist(accountAdd);
	}

}
