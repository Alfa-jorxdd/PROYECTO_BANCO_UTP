package org.banco.logica;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.banco.enums.Formato;
import org.banco.enums.TipoReporte;
import org.banco.modelos.Reporte;

import java.io.IOException;
import java.util.List;

public class ReportePdf extends Reporte{

    public ReportePdf(String nombre, TipoReporte tipoReporte) {
        super(nombre, Formato.PDF, tipoReporte);
    }

    @Override
    public void crearReporte(List<Object[]> lista) {
        try (PdfWriter writer = new PdfWriter(getNombre() + Formato.PDF.getExtension());
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)){

            document.add(crearTitulo());
            document.add(crearTabla(lista));

        } catch (IOException e){
            System.out.println("El error es " + e);
        }
    }

    private Table crearTabla(List<Object[]> lista) {
        String[] encabezados = obtenerEncabezados();
        Table tabla = new Table(new float[encabezados.length]);

        tabla.useAllAvailableWidth();

        for (String encabezado : encabezados) {
            String textEncabezado = String.valueOf(encabezado);
            tabla.addHeaderCell(crearEstiloCeldaEncabezado(textEncabezado));
        }

        tabla.setSkipFirstHeader(false);

        for (Object[] datos : lista) {
            for (Object dato : datos) {
                String textoCelda = (dato != null) ? String.valueOf(dato) : "";
                tabla.addCell(crearEstiloCeldaDato(textoCelda));
            }
        }
        return tabla;
    }

    private Paragraph crearTitulo() throws IOException {
        String tipo = getTipoReporte().toString();
        String tipoFormateado = tipo.charAt(0) + tipo.substring(1).toLowerCase();

        PdfFont fuenteTitulo = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        return new Paragraph("Reporte " + tipoFormateado)
                .setFont(fuenteTitulo)
                .setFontSize(24f)
                .setFontColor(new DeviceRgb(0, 51, 102))
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginTop(20f)
                .setMarginBottom(30f);
    }

    private Cell crearEstiloCeldaDato(String texto){
        return new Cell().add(new Paragraph(texto))
                .setTextAlignment(TextAlignment.LEFT)
                .setPadding(6f).
                setFontSize(10f)
                .setBackgroundColor(ColorConstants.WHITE)
                .setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f))
                .setKeepTogether(true);
    }

    private Cell crearEstiloCeldaEncabezado(String texto){
        return new Cell().add(new Paragraph(texto))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(6f).
                setFontSize(10f)
                .setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLUE)
                .setBorder(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.5f))
                .setKeepTogether(true);
    }
}
