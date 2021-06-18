package com.nic.pms.service;

import com.nic.pms.model.User;

public interface UserService {
	
	void save(User user);
    User findByUsername(String username);
}
