package com.uce.edu.demo.repository.modelo.dto;

import java.math.BigDecimal;

public class VehiculoVIP implements Comparable<VehiculoVIP> {

	private String placa;

	private String modelo;

	private String marca;

	private String añoFabricacion;

	private BigDecimal valorPorDia;
	
	private BigDecimal subTotal;
	
	private BigDecimal totalPagar;
	
	private String anio;

	private String mes;

	@Override
	public String toString() {
		return "VehiculoVIP [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", añoFabricacion="
				+ añoFabricacion + ", valorPorDia=" + valorPorDia + ", subTotal=" + subTotal + ", totalPagar="
				+ totalPagar + "]";
	}
	

	@Override
	public int compareTo(VehiculoVIP o) {
		// TODO Auto-generated method stub
		
		
		return this.totalPagar.compareTo(o.getTotalPagar());
	}
	
	
	
	
	// SET y GET

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAñoFabricacion() {
		return añoFabricacion;
	}

	public void setAñoFabricacion(String añoFabricacion) {
		this.añoFabricacion = añoFabricacion;
	}

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(BigDecimal totalPagar) {
		this.totalPagar = totalPagar;
	}


	
	
	

}
