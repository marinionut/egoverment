package ro.ionutmarin.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.*;
import java.util.*;

/**
 * Created by ionut on 11/15/2017.
 */
public class PdfReportGenerator {

    private void start() {
        try {
            // load report location
            FileInputStream fis = new FileInputStream("jasper.jrxml");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

            // fill report
            List<Map<String,?>> maps = new ArrayList<Map<String, ?>>();
            for (int i = 0; i < 10; i++) {
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("name", getRandomString());
                map.put("address", getRandomString());
                maps.add(map);
            }
            JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(maps);

            // compile report
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), dataSource);

            // view report to UI
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {

        }
    }

    public void start2() {
//        List<StudentVo> list = new ArrayList<StudentVo>();
//            list.add(new StudentVo("1","ashish"));
//            list.add(new StudentVo("2","deepak"));
//            list.add(new StudentVo("3","rabi"));
//            list.add(new StudentVo("4","anil"));

        // fill report
        List<Map<String,?>> mapList = new ArrayList<Map<String, ?>>();
        for (int i = 0; i < 10; i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name", getRandomString());
            map.put("address", getRandomString());
            mapList.add(map);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FileOutputStream fos = null;
            try {
            byte[] bytes = new PdfReportGenerator().generateJasperReportPDF("jasper", outputStream, mapList);
            if(bytes.length>1){
                File someFile = new File("C:\\Users\\ionut\\Desktop\\testReport.pdf");
                fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();
                System.out.println("<<<<<<<<<<<<Report Created>>>>>>>>");
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public byte[]  generateJasperReportPDF(String jasperReportName, ByteArrayOutputStream outputStream, List reportList) {
        JRPdfExporter exporter = new JRPdfExporter();
        try {
            String reportLocation = "C:\\Users\\ionut\\Desktop\\" + jasperReportName + ".jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(reportLocation);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(reportList));
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in generate Report..."+e);
        } finally {
        }
        return outputStream.toByteArray();
    }

    private String getRandomString(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        PdfReportGenerator main = new PdfReportGenerator();
        main.start2();
    }
}
