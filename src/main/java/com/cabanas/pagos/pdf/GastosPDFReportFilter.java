package com.cabanas.pagos.pdf;

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
public class GastosPDFReportFilter {

	private String fechaCaptura;
	private String fechaRegistro;
	private int tipo;
	private String concepto;
	private String proveedor;
	private double monto;	
}
