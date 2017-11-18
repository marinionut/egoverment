package ro.ionutmarin.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import ro.ionutmarin.model.Report;

import java.io.*;
import java.util.*;

/**
 * Created by ionut on 11/18/2017.
 */
public class ReportGenerator {

    public byte[]  generateJasperReportPDF(ByteArrayOutputStream outputStream, List reportList) {
        JRPdfExporter exporter = new JRPdfExporter();
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(Constants.reportTemplatePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(reportList));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generate Report..."+e);
        }

        return outputStream.toByteArray();
    }

    public void start4ChartCombined() {
        // fill report
        List<Report> list = new ArrayList<Report>();
        for (int i = 0; i < 10; i++) {
            Report data = new Report("test " + i, i ,10 - i, 10 + i);
            list.add(data);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FileOutputStream fos = null;
        try {
            byte[] bytes = generateJasperReportPDF(outputStream, list);
            if (bytes.length > 1) {
                File someFile = new File(Constants.reportPath + "report_" + String.valueOf(System.currentTimeMillis()) + ".pdf");
                fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();
                System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.start4ChartCombined();
    }
}
