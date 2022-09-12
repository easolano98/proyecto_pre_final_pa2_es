package com.uce.edu.demo.repository.modelo.dto;

import java.math.BigDecimal;

public class ReporteVIP {

	private String numero;

	private BigDecimal subTotal;

	private BigDecimal iva;

	private BigDecimal totalPagar;

	private String estadoReporte;

	private String cedula;

	private String apellido;

	private String placa;

	private String marca;

	private String modelo;

	private String fechaInicio;

	private String fechaFin;

	// SET y GET
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(BigDecimal totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getEstadoReporte() {
		return estadoReporte;
	}

	public void setEstadoReporte(String estadoReporte) {
		this.estadoReporte = estadoReporte;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
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

	
	
	
	
	
	
}
