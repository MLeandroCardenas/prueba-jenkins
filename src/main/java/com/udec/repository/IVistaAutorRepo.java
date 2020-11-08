package com.udec.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.udec.views.AutorView;

@Repository
public interface IVistaAutorRepo extends JpaRepository<AutorView, Integer> {
	
	@Query(value = "SELECT a FROM AutorView a")
	Page<AutorView> listarInformacionAutores(Pageable pageable);
}
