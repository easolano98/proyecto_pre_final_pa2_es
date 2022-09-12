package com.uce.edu.demo.repository.modelo.dto.controller;

public class ReservaDto {
	
	private String placa;
	private String cedula;
	private String fechaInicio;
	private String fechaFin;
	private String tarjetaCredito;
	
	//SET y GET
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getTarjetaCredito() {
		return tarjetaCredito;
	}
	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}
	
	
	
	
	
}
