package com.cabanas.pagos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cabanas.pagos.models.Gasto;
import com.cabanas.pagos.repositories.GastoRepository;
import com.cabanas.pagos.services.interfaces.IGasto;

@Service
public class GastoService implements IGasto {
	
	@Autowired
    private GastoRepository gastoRepository;
	
	@Override
	public List<Gasto> getAll() {
		return gastoRepository.findAll();
	}

	@Override
	public Optional<Gasto> findById(int id) {
		return gastoRepository.findById(id);
	}

	@Override
	public Gasto save(Gasto gasto) {
		
		final Optional<Gasto> gasto_ = gastoRepository.findById(gasto.getId());
		if(gasto_.isPresent()) {
			return null;
		} else {
			gastoRepository.save(gasto);
			return gasto;
		}
	}

	@Override
	public Gasto update(Gasto gasto) {
		
		final Optional<Gasto> gasto_ = gastoRepository.findById(gasto.getId());
		if(gasto_.isPresent()) {
			gasto.setId(gasto_.get().getId());
			gastoRepository.save(gasto);
			return gasto_.get();
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteById(int id) {
		
		final Optional<Gasto> gasto = gastoRepository.findById(id);
		if(gasto.isPresent()) {
			gastoRepository.delete(gasto.get());
		}
	}
	
	@Override
	public List<Gasto> getAllByFilterPDF(int tipo, String concepto, String proveedor, String monto,
			String fechaGasto) {
		return gastoRepository.getAllByFilterPDF(tipo, concepto, proveedor, monto, fechaGasto);
	}
}
