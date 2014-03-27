package com.java.learning.mvcdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.learning.mvcdatabase.dao.Offer;
import com.java.learning.mvcdatabase.dao.OfferDao;
import com.java.learning.mvcdatabase.dao.User;
import com.java.learning.mvcdatabase.dao.UsersDao;

@Service("usersService")
public class UsersService {

	private UsersDao usersDao;

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	

	public void create(User user) {
		usersDao.create(user);
	}


	public boolean exists(String username) {
		return usersDao.exists(username);
	}
	
}
