package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Vehiculo;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo>query=this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa=:datoPlaca",Vehiculo.class);
		query.setParameter("datoPlaca", placa);
		return query.getSingleResult();
	}

	@Override
	public List<Vehiculo> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo>query=this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.marca=:datoMarca",Vehiculo.class);
		query.setParameter("datoMarca", marca);
		return query.getResultList();
	}
	
	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void eliminar(String placa) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarPlaca(placa));
	}

	@Override
	public List<Vehiculo> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo>query=this.entityManager.createQuery("SELECT v FROM Vehiculo v",Vehiculo.class);
		List<Vehiculo>lista=query.getResultList();
		lista.forEach(v->v.getReservas().size());
		return lista;
	}

	
}
