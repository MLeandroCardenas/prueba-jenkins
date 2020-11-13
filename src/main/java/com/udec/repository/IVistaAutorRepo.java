package com.udec.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udec.views.AutorView;

@Repository
public interface IVistaAutorRepo extends JpaRepository<AutorView, Integer> {
}
