package com.idc.seznam.hibernate;

import java.util.List;

public interface UserDao {
	public void addUser(User user);
	public List<User> listUsers();
	public void removeUser(Integer uid);
	public void updateUser(User user);
	public User findUser(Integer uid);
	public void removeUser(User user);
}
