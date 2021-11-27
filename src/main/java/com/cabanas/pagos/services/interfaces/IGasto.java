package com.cabanas.pagos.services.interfaces;

import java.util.List;

import com.cabanas.pagos.models.Gasto;

public interface IGasto extends ICrud<Gasto> {
	
	public List<Gasto> getAllByFilterPDF(	int tipo,
											String concepto,
											String proveedor,
											String monto,
											String fechaGasto);
}
