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
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(8);
        font.setColor(Color.BLACK);
        
        for (Gasto gasto : gastos) {
        	table.addCell(new PdfPCell(new Phrase(gasto.getTipo()==1?"Ingreso":"Egreso",font)));
        	table.addCell(new PdfPCell(new Phrase(gasto.getConcepto(),font)));
        	table.addCell(new PdfPCell(new Phrase(gasto.getProveedor(),font)));
        	table.addCell(new PdfPCell(new Phrase("$" + String.valueOf(gasto.getMonto()),font)));
        	table.addCell(new PdfPCell(new Phrase(gasto.getFechaCaptura().toString(),font)));
        	table.addCell(new PdfPCell(new Phrase(gasto.getFechaRegistro().toString(),font)));
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
         
        PdfPTable table = new PdfPTable(6);
        table.setWidths(new float[] {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f});
        table.setWidthPercentage(100f);
        table.setSpacingBefore(20);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}
