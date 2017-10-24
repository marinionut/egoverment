package ro.ionutmarin.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ionut on 10/22/2017.
 */
public class PdfGenerator {
    public static String manipulatePdf(FormularEntity formularEntity, String timestamp)  {
        String pdfTemplatePath = "C:\\Users\\ionut\\Desktop\\egoverment\\greetings.pdf";
        PdfReader reader = null;
        PdfStamper stamper = null;
        String outputPdfAbsolutePath = "C:\\Users\\ionut\\Desktop\\egoverment\\pdf\\formular_" + timestamp + ".pdf";
        try {
            reader = new PdfReader(pdfTemplatePath);
            stamper = new PdfStamper(reader,
                    new FileOutputStream(outputPdfAbsolutePath));
            AcroFields fields = stamper.getAcroFields();

            fields.setField("id", String.valueOf(formularEntity.getId()));
            fields.setField("name", formularEntity.getNume());

            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return outputPdfAbsolutePath;
    }
}
