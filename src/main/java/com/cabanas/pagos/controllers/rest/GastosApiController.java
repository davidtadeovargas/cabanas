package com.cabanas.pagos.controllers.rest;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cabanas.pagos.models.Gasto;
import com.cabanas.pagos.pdf.GastoPDFExporter;
import com.cabanas.pagos.services.GastoService;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
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
		
		gasto.setFechaCaptura(new Timestamp(System.currentTimeMillis())); //Al día de hoy
		
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
		gastoService.update(gasto);
		
		return true;
	}
	
	@GetMapping("/reporte/gastos/{tipo}/{concepto}/{proveedor}/{monto}/{fechaGasto}")
    public void exportToPDF(HttpServletResponse response,
    						@PathVariable("tipo") int tipo,
    						@PathVariable("concepto") String concepto,
    						@PathVariable("proveedor") String proveedor,
    						@PathVariable("monto") String monto,
    						@PathVariable("fechaGasto") String fechaGasto) throws DocumentException, IOException {
        
		if(concepto.compareTo("N-")==0) {
			concepto = "%";
		}
		if(proveedor.compareTo("N-")==0) {
			proveedor = "%";
		}
		if(monto.compareTo("N-")==0) {
			monto = "%";
		}
		if(fechaGasto.compareTo("N-")==0) {
			fechaGasto = "%";
		}
		
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=gastos_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        final List<Gasto> gastos = gastoService.getAllByFilterPDF(tipo, concepto, proveedor, monto, fechaGasto);
         
        GastoPDFExporter exporter = new GastoPDFExporter(gastos);
        exporter.export(response);
    }
}
