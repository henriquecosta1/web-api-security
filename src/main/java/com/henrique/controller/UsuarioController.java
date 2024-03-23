package com.henrique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.model.Usuario;
import com.henrique.repository.UsuarioRepository;

@RestController
@RequestMapping("/usersList")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping()
	@ResponseBody()
	@PreAuthorize("hasAnyRole('MANAGERS','USERS')")
	public List<Usuario> getUsers() {
		return repository.findAll();
	}
	
	@GetMapping("/{username}")
	public Usuario findOne(String username) {
		return repository.findByUsername(username);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		repository.deleteById(id);
	}
	
	@PostMapping()
	public void postUser(@RequestBody Usuario usuario) {
		repository.save(usuario);
	}
	
	@PutMapping()
	public void putUser(@RequestBody Usuario usuario) {
		repository.save(usuario);
	}
}
