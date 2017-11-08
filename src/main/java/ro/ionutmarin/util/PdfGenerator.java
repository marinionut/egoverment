package ro.ionutmarin.util;

import antlr.StringUtils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ionut on 10/22/2017.
 */

public class PdfGenerator {

    public static String removeDiactritics(String s) {
        s = s.replace('ă', 'a');
        s = s.replace('ă', 'a');
        s = s.replace('ț', 't');
        s = s.replace('ș', 's');
        s = s.replace('î', 'i');
        return s;
    }

    public static String manipulatePdf(FormularEntity formularEntity, String timestamp) {
        String pdfTemplatePath = "C:\\Users\\ionut\\Desktop\\egoverment\\formular.pdf";
        PdfReader reader = null;
        PdfStamper stamper = null;
        String outputPdfAbsolutePath = "C:\\Users\\ionut\\Desktop\\egoverment\\pdf\\formular_" + timestamp + ".pdf";
        String pdfName = "formular_" + timestamp + ".pdf";

        try {
            reader = new PdfReader(pdfTemplatePath);
            stamper = new PdfStamper(reader,
                    new FileOutputStream(outputPdfAbsolutePath));
            AcroFields fields = stamper.getAcroFields();
            fields.getFields();

            fields.setField("nume", formularEntity.getNume());
            fields.setField("prenume", formularEntity.getPrenume());
            fields.setField("initialaTata", formularEntity.getInitialaTata());
            fields.setField("cnp", formularEntity.getCnp());
            fields.setField("adresa", formularEntity.getAdresa());
            fields.setField("judet", removeDiactritics(formularEntity.getJudet()));
            fields.setField("localitate", removeDiactritics(formularEntity.getLocalitate()));
            fields.setField("telefon", formularEntity.getTelefon());
            fields.setField("beneficiar", formularEntity.getBeneficiar());
            fields.setField("codIdentificare", formularEntity.getCodIdentificare());
            fields.setField("cont", formularEntity.getCont());
            fields.setField("sumaTotala", String.valueOf(formularEntity.getSumaTotala()));
            fields.setField("data", new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()).toString());


            stamper.setFormFlattening(true);
            stamper.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return pdfName;
    }
}
