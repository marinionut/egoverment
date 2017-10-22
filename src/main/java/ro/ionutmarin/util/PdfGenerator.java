package ro.ionutmarin.util;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ro.ionutmarin.entity.GreetingsEntity;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ionut on 10/22/2017.
 */
public class PdfGenerator {
    public static void manipulatePdf(GreetingsEntity greetingsEntity, String timestamp)  {
        String pdfTemplatePath = "C:\\Users\\ionut\\Desktop\\greetings.pdf";
        PdfReader reader = null;
        PdfStamper stamper = null;

        try {
            reader = new PdfReader(pdfTemplatePath);
            stamper = new PdfStamper(reader,
                    new FileOutputStream("C:\\Users\\ionut\\Desktop\\greetings_" + timestamp + ".pdf"));
            AcroFields fields = stamper.getAcroFields();

            fields.setField("id", String.valueOf(greetingsEntity.getId()));
            fields.setField("name", greetingsEntity.getContent());

            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
