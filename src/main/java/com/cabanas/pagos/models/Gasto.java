package com.cabanas.pagos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int tipo; //0 - ingreso 1 - egreso
	private String concepto;
	private String proveedor;
	private double monto;
	private java.sql.Timestamp fechaCaptura; //Fecha del día forzosa no se puede cambiar
	private java.sql.Timestamp fechaRegistro; //Fecha de cuando se hizo la compra real
}
