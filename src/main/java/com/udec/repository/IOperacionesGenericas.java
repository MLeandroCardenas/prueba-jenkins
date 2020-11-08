package com.udec.repository;
import org.springframework.data.domain.Page;

public interface IOperacionesGenericas<T, V> {
	void guardar(T entidad);
	void eliminar(V id);
	void editar(T entidad);
	T listarPorId(boolean lazy, V id);
	Page<T>listarTodos(boolean lazy, V page, V size);
}
