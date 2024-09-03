package com.aegis.test.service;

import com.aegis.test.model.Organization;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface OrgService {
	Page<Organization> getList(int page, int size);
	String createData(Organization data);
	String updateData(Organization data, UUID id);
	String deleteData(UUID id);
}
