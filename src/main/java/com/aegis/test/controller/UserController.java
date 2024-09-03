package com.aegis.test.controller;

import com.aegis.test.dto.ResetPasswordDto;
import com.aegis.test.model.User;
import com.aegis.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService _service;

	@PutMapping("/{id}")
	public Map<String, Object> updateUser(@RequestBody User user, @PathVariable UUID id) {
		String message = _service.updateUser(user, id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteUser(@PathVariable UUID id) {
		String message = _service.deleteUser(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}
	
	@GetMapping("")
	public Map<String, Object> getListUser(
			@RequestParam(name = "page", defaultValue = "0") int page,
		    @RequestParam(name = "size", defaultValue = "10") int size
			){
		Page<User> listUser = _service.getList(page, size);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "success");
		result.put("status", 200);
		result.put("data", listUser.getContent());
		result.put("count", listUser.getSize());
		result.put("totalPage", listUser.getTotalPages());
		result.put("totalElement", listUser.getTotalElements());
		
		return result;
	}
}
