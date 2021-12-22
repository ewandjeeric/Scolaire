package org.etudiant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.etudiant.dao.EtudiantRepository;
import org.etudiant.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantRestService {

	@Autowired
	private EtudiantRepository etudiantrepository;

	@RequestMapping(value = "/etudiants", method = RequestMethod.POST)
	@Secured(value = { "ROLE_ADMIN", "ROLE_SCOLARITE" })
	public Object saveEtudiant(@RequestBody @Valid Etudiant e, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			Map<String, Object> errors = new HashMap<>();
			errors.put("errors", true);
			// List<FieldError> error = bindingResult.getFieldErrors();
			for (FieldError fe : bindingResult.getFieldErrors()) {
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		return etudiantrepository.save(e);
	}

	@RequestMapping(value = "/etudiants", method = RequestMethod.GET)
	@Secured(value = { "ROLE_ADMIN", "ROLE_SCOLARITE", "ROLE_PROF", "ROLE_ETUDIANT" })
	public Page<Etudiant> listEtudiant(int page, int size) {
		return etudiantrepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "idEtudiant")));
	}

	@RequestMapping(value = "/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpserveletrequest) {
		HttpSession httpsession = httpserveletrequest.getSession();
		SecurityContext securitycontext = (SecurityContext) httpsession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = securitycontext.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority ga : securitycontext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;

	}

}
