package com.zdy.school.factory;

import com.zdy.school.dao.LoginDao;
import com.zdy.school.impl.LoginDaoImpl;


public class DaoFactory {
	
	//登陆
	public static LoginDao getUsersInstances() {
		return new LoginDaoImpl();
	}

}
