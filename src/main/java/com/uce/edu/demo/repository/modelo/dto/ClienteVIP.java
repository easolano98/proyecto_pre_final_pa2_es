package com.uce.edu.demo.repository.modelo.dto;

import java.math.BigDecimal;

public class ClienteVIP implements Comparable<ClienteVIP> {

	private String cedula;

	private String nombre;

	private String apellido;

	private BigDecimal valorTotal;

	private BigDecimal iva;

	@Override
	public int compareTo(ClienteVIP o) {
		// TODO Auto-generated method stub

		return this.valorTotal.compareTo(o.getValorTotal());
	}

	// SET y GET
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getIva() {
		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	@Override
	public String toString() {
		return "ClienteVIP [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", valorTotal="
				+ valorTotal + ", iva=" + iva + "]";
	}

}
