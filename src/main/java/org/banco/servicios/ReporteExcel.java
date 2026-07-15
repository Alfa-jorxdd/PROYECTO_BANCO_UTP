package org.banco.servicios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.banco.enums.Formato;
import org.banco.enums.TipoReporte;

public class ReporteExcel extends Reporte{

    public ReporteExcel(String nombre, TipoReporte tipoReporte) {
        super(nombre, Formato.EXCEL, tipoReporte);
    }

    @Override
    public void crearReporte(List<Object[]> lista) {
        Workbook libro = new XSSFWorkbook();
        Sheet pagina = libro.createSheet("Reporte");

        int filaActual = 0;

        CellStyle estiloTitulo = crearEstiloTitulo(libro);
        CellStyle estiloEncabezado = crearEstiloEncabezado(libro);
        CellStyle estiloDato = crearEstiloDato(libro);

        String[] encabezados = obtenerEncabezados();

        Row filaTitulo = pagina.createRow(filaActual++);
        Cell celdaTitulo = filaTitulo.createCell(0);
        String tipo = getTipoReporte().toString();
        String tipoFormateado = tipo.charAt(0) + tipo.substring(1).toLowerCase();
        celdaTitulo.setCellValue("Reporte de " + tipoFormateado);
        celdaTitulo.setCellStyle(estiloTitulo);
        pagina.addMergedRegion(new CellRangeAddress(0, 0, 0, Math.max(encabezados.length - 1, 0)));

        filaActual++;

        // Encabezados
        Row filaEncabezado = pagina.createRow(filaActual++);
        for (int j = 0; j < encabezados.length; j++) {
            Cell celda = filaEncabezado.createCell(j);
            celda.setCellValue(encabezados[j]);
            celda.setCellStyle(estiloEncabezado);
        }

        // Datos
        for (int i = 0; i < lista.size(); i++) {
            Row fila = pagina.createRow(filaActual++);
            Object[] datos = lista.get(i);
            for (int j = 0; j < datos.length; j++) {
                Cell celda = fila.createCell(j);
                escribirCelda(celda, datos[j], estiloDato);
            }
        }

        guardarReporte(libro);
    }

    private void escribirCelda(Cell celda, Object valor, CellStyle estiloDato) {
        if (valor == null) {
            celda.setCellValue("");
        } else if (valor instanceof Integer) {
            celda.setCellValue((Integer) valor);
        } else if (valor instanceof Double) {
            celda.setCellValue((Double) valor);
        } else if (valor instanceof Long) {
            celda.setCellValue((Long) valor);
        } else if (valor instanceof Boolean) {
            celda.setCellValue((Boolean) valor);
        } else {
            celda.setCellValue(valor.toString());
        }

        celda.setCellStyle(estiloDato);
    }

    private CellStyle crearEstiloTitulo(Workbook libro) {
        Font fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeightInPoints((short) 16);
        fuente.setColor(IndexedColors.DARK_BLUE.getIndex());

        CellStyle estilo = libro.createCellStyle();
        estilo.setFont(fuente);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        return estilo;
    }

    private CellStyle crearEstiloEncabezado(Workbook libro) {
        Font fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setColor(IndexedColors.WHITE.getIndex());

        CellStyle estilo = libro.createCellStyle();
        estilo.setFont(fuente);
        estilo.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        return estilo;
    }

    private CellStyle crearEstiloDato(Workbook libro) {
        CellStyle estilo = libro.createCellStyle();
        estilo.setBorderBottom(BorderStyle.THIN);
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        return estilo;
    }

    private void guardarReporte(Workbook libro){
        try {
            FileOutputStream out = new FileOutputStream(getNombre() + Formato.EXCEL.getExtension());
            libro.write(out);
            out.close();
            libro.close();
        } catch (IOException e) {
            System.out.println("El error es: " + e);
        }
    }
}
