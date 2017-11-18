/*
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

*/
/**
 * Created by ionut on 11/15/2017.
 *//*

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

    public void start4Table() {
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
                File someFile = new File("C:\\Users\\ionut\\Desktop\\egoverment\\report\\testReport.pdf");
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
            String reportLocation = "C:\\Users\\ionut\\Desktop\\egoverment\\" + jasperReportName + ".jrxml";
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

    public void start4Charts() {
//        List<StudentVo> list = new ArrayList<StudentVo>();
//            list.add(new StudentVo("1","ashish"));
//            list.add(new StudentVo("2","deepak"));
//            list.add(new StudentVo("3","rabi"));
//            list.add(new StudentVo("4","anil"));

        // fill report
        // fill report
        List<Map<String,?>> maps = new ArrayList<Map<String, ?>>();
        for (int i = 0; i < 10; i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name", getRandomString().substring(0,5));
            map.put("mark", 50 - i);
            maps.add(map);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FileOutputStream fos = null;
        try {
            byte[] bytes = new PdfReportGenerator().generateJasperReportPDF("chart", outputStream, maps);
            if(bytes.length>1){
                File someFile = new File("C:\\Users\\ionut\\Desktop\\egoverment\\report\\chart.pdf");
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

    public class ChartData {
        String name;
        int q1;
        int q2;

        public ChartData(String name, int q1, int q2) {
            this.name = name;
            this.q1 = q1;
            this.q2 = q2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQ1() {
            return q1;
        }

        public void setQ1(int q1) {
            this.q1 = q1;
        }

        public int getQ2() {
            return q2;
        }

        public void setQ2(int q2) {
            this.q2 = q2;
        }
    }

    public void start4ChartBar() {


        // fill report
        List<ChartData> list = new ArrayList<ChartData>();
        for (int i = 0; i < 10; i++) {
            ChartData data = new ChartData("test " + i, 10 - i, 9-i);
            list.add(data);
        }

        //name, q1, q2
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        FileOutputStream fos = null;
        try {
            byte[] bytes = new PdfReportGenerator().generateJasperReportPDF("chartBar", outputStream, list);
            if(bytes.length>1){
                File someFile = new File("C:\\Users\\ionut\\Desktop\\egoverment\\report\\chartBar.pdf");
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
            byte[] bytes = new PdfReportGenerator().generateJasperReportPDF("combinedChart", outputStream, list);
            if(bytes.length>1){
                File someFile = new File("C:\\Users\\ionut\\Desktop\\egoverment\\report\\combinedChart.pdf");
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

    public static void main(String[] args) {
        PdfReportGenerator main = new PdfReportGenerator();
        //main.start4Table();
        //main.start4Charts();
        //main.start4ChartBar();
        main.start4ChartCombined();
    }
}
*/
