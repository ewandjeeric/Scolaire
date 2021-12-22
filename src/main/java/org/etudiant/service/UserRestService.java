package org.etudiant.service;

import java.util.List;

import org.etudiant.dao.RoleRepository;
import org.etudiant.dao.UserRepository;
import org.etudiant.entities.Role;
import org.etudiant.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured(value = { "ROLE_ADMIN" })
public class UserRestService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private RoleRepository rolerepository;

	@RequestMapping(value = "/AddUser")
	public User save(User u) {
		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		return userrepository.save(u);
	}

	@RequestMapping(value = "/findUsers")
	public List<User> allUser() {
		return userrepository.findAll();
	}

	@RequestMapping(value = "/AddRole")
	public Role saveRole(Role r) {
		return rolerepository.save(r);
	}

	@RequestMapping(value = "/findRole")
	public List<Role> allRole() {
		return rolerepository.findAll();
	}

	@RequestMapping(value = "/addRoleToUser")
	public User AddRoleToUser(String username, String role) {
		User u = userrepository.findById(username).orElse(null);
		Role r = rolerepository.findById(role).orElse(null);
		u.getRoles().add(r);
		userrepository.save(u);
		return u;
	}

}
