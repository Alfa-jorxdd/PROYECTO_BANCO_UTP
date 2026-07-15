package org.banco.servicios;

import org.banco.enums.Formato;
import org.banco.enums.TipoReporte;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReporteHtml extends Reporte{

    public ReporteHtml(String nombre, TipoReporte tipoReporte) {
        super(nombre, Formato.HTML, tipoReporte);
    }

    @Override
    public void crearReporte(List<Object[]> lista) {

        String rutaArchivo = getNombre() + getFormato().getExtension();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("<!DOCTYPE html>\n<html>\n<head>\n");
            writer.write("<meta charset='UTF-8'>\n");
            writer.write("<title>Reporte</title>\n");


            writer.write("<style>\n");
            writer.write("  body { font-family: Arial, sans-serif; margin: 20px; }\n");
            writer.write("  h1 { text-align: center; color: rgb(0, 51, 102); margin-top: 20px; margin-bottom: 30px; font-size: 24px; }\n");
            writer.write("  table { width: 100%; border-collapse: collapse; page-break-inside: auto; }\n");

            writer.write("  tr { page-break-inside: avoid; page-break-after: auto; }\n");

            writer.write("  td { text-align: left; padding: 6px; font-size: 14px; background-color: #ffffff; border: 0.5px solid #d3d3d3; }\n");

            writer.write("  th { text-align: center; padding: 6px; font-size: 14px; background-color: #0000ff; color: #ffffff; border: 0.5px solid #d3d3d3; }\n");

            writer.write("  @media print { thead { display: table-header-group; } }\n");
            writer.write("</style>\n</head>\n<body>\n");

            writer.write(crearTituloHtml());

            writer.write(crearTablaHtml(lista));

            writer.write("</body>\n</html>");
        } catch (IOException e) {
            System.out.println("El error en HTML es " + e);
        }
    }

    private String crearTituloHtml() {
        String tipo = getTipoReporte().toString();
        String tipoFormateado = tipo.charAt(0) + tipo.substring(1).toLowerCase();
        return "<h1>Reporte " + tipoFormateado + "</h1>\n";
    }

    private String crearTablaHtml(List<Object[]> lista) {
        StringBuilder html = new StringBuilder();
        html.append("<table>\n");

        html.append("  <thead>\n    <tr>\n");
        String[] encabezados = obtenerEncabezados();
        for (String encabezado : encabezados) {
            html.append("      <th>").append(encabezado).append("</th>\n");
        }
        html.append("    </tr>\n  </thead>\n");

        html.append("  <tbody>\n");
        for (Object[] datos : lista) {
            html.append("    <tr>\n");
            for (Object dato : datos) {
                String textoCelda = (dato != null) ? String.valueOf(dato) : "";
                html.append("      <td>").append(textoCelda).append("</td>\n");
            }
            html.append("    </tr>\n");
        }
        html.append("  </tbody>\n");
        html.append("</table>\n");

        return html.toString();
    }
    
}
