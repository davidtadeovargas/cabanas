package com.cabanas.pagos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cabanas.pagos.models.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

	 @Query(value = "SELECT * FROM gasto WHERE gasto.tipo = :tipo AND CASE WHEN :concepto = '%' THEN 1=1 ELSE gasto.concepto LIKE('%'+:concepto+'%') END AND CASE WHEN :proveedor = '%' THEN 1=1 ELSE gasto.proveedor LIKE('%'+:proveedor+'%') END AND CASE WHEN :monto = '%' THEN 1=1 ELSE gasto.monto = :monto END AND CASE WHEN :fechaGasto = '%' THEN 1=1 ELSE date(gasto.fecha_registro) = date(:fechaGasto) END", nativeQuery = true)
	 public List<Gasto> getAllByFilterPDF(	@Param("tipo") int tipo,
 											@Param("concepto") String concepto,
 											@Param("proveedor") String proveedor,
 											@Param("monto") String monto,
 											@Param("fechaGasto") String fechaGasto);
}
