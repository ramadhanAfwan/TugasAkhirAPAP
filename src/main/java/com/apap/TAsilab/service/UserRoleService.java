package com.apap.TAsilab.service;

import com.apap.TAsilab.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	UserRoleModel findUserByUsername(String username);
	public String encrypt(String password);
	public void changePassword(UserRoleModel user, String newPassword);
}
