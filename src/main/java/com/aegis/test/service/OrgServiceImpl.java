package com.aegis.test.service;


import com.aegis.test.model.Organization;
import com.aegis.test.repository.OrganizationRepository;
import com.aegis.test.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrgServiceImpl implements OrgService {
	
	private OrganizationRepository repository;

	public OrgServiceImpl(
			OrganizationRepository _repository
			) {
        this.repository = _repository;
    }

	@Override
	public Page<Organization> getList(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
	}


	@Override
	public String createData(Organization data) {
		Organization resultObj = repository.save(data);
		String result = "Create Data Failed";
		if(resultObj != null) {
			result = "Create Data Success";
		}
		
		return result;
	}

	@Override
	public String updateData(Organization data, UUID id) {
		Optional<Organization> resultObj = repository.findById(id);
		String result = "Update Data Failed";
		if(resultObj.isPresent()) {
			Organization nData = resultObj.get();
			nData.setName(data.getName());
			nData.setDescriptions(data.getDescriptions());

			repository.save(nData);
			result = "Update Data Success";
		}	
		return result;
	}

	@Override
	public String deleteData(UUID id) {
		try {
			repository.deleteById(id);
			String result = "delete Data Success";
			return result;
		} catch (Exception e) {
			String result = e.getMessage();
			return result;
		}
	}
}
