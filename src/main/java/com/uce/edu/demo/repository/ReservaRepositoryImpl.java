package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva buscarNumero(String numero) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> query = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.numero=:datoNumero",
				Reserva.class);
		query.setParameter("datoNumero", numero);
		Reserva r = query.getSingleResult();
		r.getVehiculo().getId();
		return r;
	}

	@Override
	public List<Reserva> buscarPorVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> query = this.entityManager
				.createQuery("SELECT r FROM Reserva r WHERE r.vehiculo=:datoVehiculo", Reserva.class);
		query.setParameter("datoVehiculo", vehiculo);
		return query.getResultList();
	}

	@Override
	public void actualizar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public void eliminar(String numero) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarNumero(numero));
	}

	@Override
	public List<Reserva> reporte(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> query = this.entityManager.createQuery(
				"SELECT r FROM Reserva r WHERE r.fechaInicio>=:fechaInicio AND r.fechaFin<=:fechaFin", Reserva.class);
		query.setParameter("fechaInicio", fechaInicio);
		query.setParameter("fechaFin", fechaFin);

		List<Reserva> lista = query.getResultList();
		lista.stream().forEach(r -> {
			r.getCliente().getCedula();
			r.getVehiculo().getPlaca();
		});
		

		return lista;
	}

}
