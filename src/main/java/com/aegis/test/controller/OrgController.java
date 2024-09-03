package com.aegis.test.controller;

import com.aegis.test.dto.ResetPasswordDto;
import com.aegis.test.model.Organization;
import com.aegis.test.service.OrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrgController {
	
	@Autowired
	private OrgService _service;

	@PostMapping("")
	public Map<String, Object> createData(@RequestBody Organization data) {
		String message = _service.createData(data);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}

	@PutMapping("/{id}")
	public Map<String, Object> updateData(@RequestBody Organization data, @PathVariable UUID id) {
		String message = _service.updateData(data, id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}

	@DeleteMapping("/{id}")
	public Map<String, Object> deleteData(@PathVariable UUID id) {
		String message = _service.deleteData(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", message);
		result.put("status", 200);
		
		return result;
	}
	
	@GetMapping("")
	public Map<String, Object> getListData(
			@RequestParam(name = "page", defaultValue = "0") int page,
		    @RequestParam(name = "size", defaultValue = "10") int size
			){
		Page<Organization> listdata = _service.getList(page, size);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("message", "success");
		result.put("status", 200);
		result.put("data", listdata.getContent());
		result.put("count", listdata.getSize());
		result.put("totalPage", listdata.getTotalPages());
		result.put("totalElement", listdata.getTotalElements());
		
		return result;
	}
	
}
