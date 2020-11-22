package com.udec.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.udec.entity.Autor;
import com.udec.entity.Usuario;
import com.udec.exception.BussinesException;
import com.udec.exception.ModelNotFoundException;
import com.udec.repository.IUsuarioRepo;
import com.udec.service.IUsuarioService;

@Service
public class UsuarioServiceImp implements IUsuarioService, UserDetailsService {
	
	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	public boolean validarCamposUsuario(Usuario usuario) {
		if(usuario.getRol().getIdRol() == null)
			throw new BussinesException("el id del rol es requerido");
		else if(repo.existsByNick(usuario.getNick()))
			throw new BussinesException("Ya existe un usuario con el nick " + usuario.getNick());
		else if(repo.existsByDocumento(usuario.getDocumento()))
			throw new BussinesException("Ya existe un usuario con el documento " + usuario.getDocumento());
		else
			return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findByNick(nick);
		if(usuario == null)
			throw new ModelNotFoundException("No existe ese usuario");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		
		UserDetails credenciales = new User(usuario.getNick(), usuario.getClave(), roles);
		return credenciales;
	}
	
	@Override
	public void guardar(Usuario usuario) {
		if(validarCamposUsuario(usuario)) {
			usuario.setClave(bcrypt.encode(usuario.getClave()));
			repo.save(usuario);
		}
	}
	
	@Override
	public Page<Usuario> listarTodos(boolean lazy, Integer page, Integer size) {
		Page<Usuario> listaUsuarios = repo.findAll(PageRequest.of(page, size, Sort.by("apellido").ascending())); 
		for (Usuario usuario : listaUsuarios) {
			usuario.setClave(null);
		}
		return listaUsuarios;
	}	
	
	@Override
	public Usuario listarPorId(boolean lazy, Integer id) {
		Usuario usuario = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("No existe un usuario con el id " + id));
		usuario.setClave(null);
		return usuario;
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Usuario entidad) {
		// TODO Auto-generated method stub
		
	}
}
