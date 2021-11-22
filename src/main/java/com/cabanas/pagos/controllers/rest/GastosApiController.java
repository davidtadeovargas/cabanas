package com.cabanas.pagos.controllers.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabanas.pagos.models.Gasto;
import com.cabanas.pagos.services.GastoService;

@RestController
@RequestMapping("/api/gasto")
public class GastosApiController {

	//Capa de servicio a usuarios
	@Autowired
	GastoService gastoService;
	
	//Obtiene todos los gastos
	@GetMapping("/getall")
	public List<Gasto> getAll(){
		return gastoService.getAll();
	}
	
	//Obtiene por ID
	@GetMapping("/get/{id}")
	public Gasto getById(@PathVariable("id") int id){
		final Optional<Gasto> gasto = gastoService.findById(id);
		if(gasto.isPresent()) {
			return gasto.get();
		} else {
			return null;
		}
	}
	
	//Borra un gasto
	@PostMapping("/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		gastoService.deleteById(id);
		return true;
	}
	
	//Crea un gasto
	@PostMapping("/save")
	public boolean save(@Valid @RequestBody Gasto gasto) {
		
		//Crea el nuevo gasto
		gastoService.save(gasto);
		
		return true;
	}
	
	//Actualiza un gasto
	@PostMapping("/update/{id}")
	public boolean update(@PathVariable("id") int id, @Valid @RequestBody Gasto gasto) {
		
		//Si el gasto no existe no permitas avanzar
		Optional<Gasto> gasto_ = gastoService.findById(id);
		if(gasto_==null) {
			return false;
		}
		
		//Actualiza el gasto
		gasto.setId(id);
		gastoService.save(gasto);
		
		return true;
	}
}
