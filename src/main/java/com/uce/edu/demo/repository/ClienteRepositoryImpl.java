package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> query = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.cedula=:datoCedula ", Cliente.class);
		query.setParameter("datoCedula", cedula);
		return query.getSingleResult();
	}

	@Override
	public List<Cliente> buscarApellido(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> query = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.apellido=:datoApellido ", Cliente.class);
		query.setParameter("datoApellido", apellido);
		return query.getResultList();
	}

	@Override
	public List<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> query = this.entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);

		List<Cliente> lista = query.getResultList();
		lista.forEach(c -> c.getReservas().size());
		return lista;
	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
//		Query myQuery = this.entityManager.createQuery(
//				"UPDATE Cliente c SET c.nombre=:datoNombre, c.apellido=:datoApellido, c.fechaNacimiento=:datoFechaNacimiento, c.genero=:datoGenero WHERE c.cedula = :datoCedula");
//		myQuery.setParameter("datoNombre", cliente.getNombre());
//		myQuery.setParameter("datoApellido", cliente.getApellido());
//		myQuery.setParameter("datoFechaNacimiento", cliente.getFechaNacimiento());
//		myQuery.setParameter("datoGenero", cliente.getGenero());
//		myQuery.setParameter("datoCedula", cliente.getCedula());
		 this.entityManager.merge(cliente);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(id));
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarCedula(cedula));
	}

}
