package utilidades;


import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import gui.PanelConsultaMovimientos;

import java.io.*;
import java.text.SimpleDateFormat; 

/**
 * Example of using the iText library to work with PDF documents on Java, 
 * lets you create, analyze, modify and maintain documents in this format.
 * Ejemplo de uso de la librería iText para trabajar con documentos PDF en Java, 
 * nos permite crear, analizar, modificar y mantener documentos en este formato.
 *
 * @author xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi web http://www.codigoxules.org
 */
public class GeneratePDFFileIText {
    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    
    private PanelConsultaMovimientos w;
    
    private static final String iTextExampleImage = "Iconos/logo.jpg";
    /**
     * We create a PDF document with iText using different elements to learn 
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender 
     * a usar esta librería.
     * @param pdfNewFile  <code>String</code> 
     *      pdf File we are going to write. 
     *      Fichero pdf en el que vamos a escribir. 
     */
    public void createPDF(PanelConsultaMovimientos window) {
    	this.w=window;
        try {
            Document document = new Document(PageSize.A4.rotate());;
            FileOutputStream archivo;
    		try {
				archivo = new FileOutputStream("ConsultaMovimientos.pdf");
				PdfWriter.getInstance(document, archivo);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            document.open();
            
            document.addTitle("Consulta Movimientos");
            document.addSubject("Consulta Movimientos");
            document.addKeywords("Java, PDF, Consulta");
            document.addAuthor("CETEC");
            document.addCreator("CETEC");
            
            // First page
            // Primera página 
           
            Image image;
            try {
                image = Image.getInstance(iTextExampleImage);  
                image.scaleToFit(50, 50);
                document.add(image);
            } catch (BadElementException ex) {
                System.out.println("Image BadElementException" +  ex);
            } catch (IOException ex) {
                System.out.println("Image IOException " +  ex);
            }
            document.add(new Paragraph("CONSULTA DE MOVIMIENTOS",
    				FontFactory.getFont("arial",   // fuente
    				22,                            // tama�o
    				Font.BOLD,                   // estilo
    				BaseColor.BLUE)));   
            document.add(new Paragraph("C�digo del trabajo: " + w.getTextoTrabajo().getText() + ".", paragraphFont));
            document.add(new Paragraph("Par�metros de b�squeda:", paragraphFont));
            document.add(new Paragraph(
    				"De c�digo operario  " + w.getTextoOperarioIni().getText() + " a " + w.getTextoOperarioFin().getText() + ".",paragraphFont));
    		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
    		document.add(new Paragraph(
    				"De fecha  " + d.format(w.getFechaChooser1().getDate()) + " a " + d.format(w.getFechaChooser2().getDate()) + ".", paragraphFont));
    		document.add(new Paragraph("\n\n"));
            
            Integer numColumns = 7;
            Integer numRows = w.getModelo().getRowCount()*7;
            PdfPTable table = new PdfPTable(numColumns); 
            PdfPCell columnHeader;
            
			columnHeader = new PdfPCell(new Phrase("Fecha"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Operario"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Concepto"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Descripci�n"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Horas"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Precio"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
			columnHeader = new PdfPCell(new Phrase("Importe"));
			columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(columnHeader);
            
            table.setHeaderRows(1);
            for (int row = 0; row < numRows; row++) {
                for (int column = 0; column < numColumns; column++) {
                    table.addCell("Row " + row + " - Col" + column);
                }
            }
            document.add(table);
            document.close();
            System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
        } catch (DocumentException documentException) {
            System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        }
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        GeneratePDFFileIText generatePDFFileIText = new GeneratePDFFileIText();
//        generatePDFFileIText.createPDF();
//    }
}
