package com.idc.seznam.hibernate;

import java.util.List;

public interface UserServiceInterface {

	//public abstract void printUsers();

	public abstract void saveUser(User user);

	public abstract List<User> getUsers();

	public abstract void deleteUserByUid(int uid);

	public abstract User getUserByUid(int uid);

}