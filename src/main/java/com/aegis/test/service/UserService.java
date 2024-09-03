package com.aegis.test.service;

import com.aegis.test.dto.ResetPasswordDto;
import com.aegis.test.model.User;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface UserService {
	Page<User> getList(int page, int size);
	String createUser(User user);
	String updateUser(User user, UUID id);
	String deleteUser(UUID id);
	String resetPassword(ResetPasswordDto reset);
}
