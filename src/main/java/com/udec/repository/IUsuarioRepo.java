package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer> {
	
	
	Usuario findByNick(String nick);
	
	boolean existsByNick(String nick);
	
	boolean existsByDocumento(String documento);
}
