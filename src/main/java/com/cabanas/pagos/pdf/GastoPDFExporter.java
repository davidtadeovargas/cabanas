package com.cabanas.pagos.pdf;

import com.cabanas.pagos.models.Gasto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class GastoPDFExporter {

	private List<Gasto> gastos;
	
	
	public GastoPDFExporter(final List<Gasto> gastos){
		this.gastos = gastos;
	}
	
	private void writeTableHeader(PdfPTable table) {
		
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize((float)8.0);
         
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Concepto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Proveedor", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Monto", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("F.Captura", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("F.Registro", font));
        table.addCell(cell);
    }
	
	private void writeTableData(PdfPTable table) {
        for (Gasto gasto : gastos) {
        	table.addCell(gasto.getTipo()==1?"Ingreso":"Egreso");
            table.addCell(gasto.getConcepto());
            table.addCell(gasto.getProveedor());
            table.addCell(String.valueOf(gasto.getMonto()));
            table.addCell(gasto.getFechaCaptura().toString());
            table.addCell(gasto.getFechaRegistro().toString());
        }
    }
    
    public void export(HttpServletResponse response) throws DocumentException, IOException {
    
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de gastos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
         float[] colWidths = {2,2,2,2};
        PdfPTable table = new PdfPTable(colWidths);
        //table.setWidths(new float[] {1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
