package com.aegis.test.service;


import com.aegis.test.dto.ResetPasswordDto;
import com.aegis.test.model.User;
import com.aegis.test.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	private final PasswordEncoder passwordEncoder;
	
	private UserRepository repository;

	public UserServiceImpl(
			UserRepository _repository,
			PasswordEncoder passwordEncoder
			) {
        this.repository = _repository;
		this.passwordEncoder = passwordEncoder;
    }

	@Override
	public Page<User> getList(int page, int size) {
		return repository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
	}


	@Override
	public String createUser(User user) {
		User resultObj = repository.save(user);
		String result = "Create Data Failed";
		if(resultObj != null) {
			result = "Create Data Success";
		}
		
		return result;
	}

	@Override
	public String updateUser(User user, UUID id) {
		Optional<User> resultObj = repository.findById(id);
		String result = "Update Data Failed";
		if(resultObj.isPresent()) {
			User nUser = resultObj.get();
			nUser.setName(user.getName());
			nUser.setEmail(user.getEmail());
			nUser.setRole(user.getRole());

			repository.save(nUser);
			result = "Update Data Success";
		}	
		return result;
	}

	@Override
	public String deleteUser(UUID id) {
		try {
			repository.deleteById(id);
			String result = "delete Data Success";
			return result;
		} catch (Exception e) {
			String result = e.getMessage();
			return result;
		}
	}

	@Override
	public String resetPassword(ResetPasswordDto reset) {
		Optional<User> resultObj = repository.findByEmail(reset.getEmail());
		String result = "Update Data Failed";
		if(resultObj.isPresent()) {
			User nUser = resultObj.get();
			String oldPassHash = passwordEncoder.encode(reset.getOldPassword());

			System.out.println(oldPassHash);
			System.out.println(nUser.getPassword());

			if (passwordEncoder.matches(reset.getOldPassword(), nUser.getPassword()) &&
            reset.getNewPassword().equals(reset.getConfirmPassword())) {
				String newPassHash = passwordEncoder.encode(reset.getNewPassword());
				nUser.setPassword(newPassHash);
				repository.save(nUser);
				result = "Update Data Success";
			}else{
				result = "Old Passowrd / New Password with confirmation password is different";
			}
		}	
		return result;
	}
}
