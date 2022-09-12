package com.uce.edu.demo.repository.modelo.dto.controller;

import java.math.BigDecimal;

import javax.persistence.Column;

public class VehiculoDTO {

	private String id;

	private String placa;

	private String modelo;

	private String marca;

	private String anioFabricacion;

	private String paisFabricacion;

	private String cilindraje;

	private BigDecimal avaluo;

	private BigDecimal valorPorDia;

	private String estado;
}
