package ro.ionutmarin.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import ro.ionutmarin.model.Report;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ionut on 11/18/2017.
 */
public class ReportGenerator {

    private static byte[] generateJasperReportPDF(ByteArrayOutputStream outputStream, List reportList) {
        JRPdfExporter exporter = new JRPdfExporter();
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(Constants.reportTemplatePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(reportList));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generate Report..." + e);
        }

        return outputStream.toByteArray();
    }

    public static String getReportPath(List<Report> list) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FileOutputStream fos = null;
        String reportName = Constants.reportPath + "report_" + String.valueOf(System.currentTimeMillis()) + ".pdf";
        try {
            byte[] bytes = generateJasperReportPDF(outputStream, list);
            if (bytes.length > 1) {
                File someFile = new File(reportName);
                fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();
                System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
            }

            return reportName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "empty";
    }
}
