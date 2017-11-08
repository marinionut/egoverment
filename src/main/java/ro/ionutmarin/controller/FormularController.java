package ro.ionutmarin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ionutmarin.model.Formular;
import ro.ionutmarin.model.Greetings;
import ro.ionutmarin.model.TipVenit;
import ro.ionutmarin.service.FormularService;
import ro.ionutmarin.util.Constants;
import ro.ionutmarin.util.PdfKeeper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ionut on 10/21/2017.
 */
@RequestMapping("/formular")
@RestController
public class FormularController {
    @Autowired
    FormularService formularService;

    PdfKeeper pdfKeeper = new PdfKeeper();

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PdfKeeper sayHello(@RequestParam(value="nume", required=false, defaultValue="Stranger") String nume,
                       @RequestParam(value="prenume", required=false) String prenume,
                       @RequestParam(value="initialaTata", required=false) String initialaTata,
                       @RequestParam(value="cnp", required=false) String cnp,
                       @RequestParam(value="adresa", required=false) String adresa,
                       @RequestParam(value="judet", required=false) String judet,
                       @RequestParam(value="localitate", required=false) String localitate,
                       @RequestParam(value="telefon", required=false, defaultValue = "-") String telefon,
                       @RequestParam(value="email", required=false, defaultValue = "-") String email,
                       @RequestParam(value="tipVenit", required=false) Integer tipVenit,
                       @RequestParam(value="beneficiar", required=false) String beneficiar,
                       @RequestParam(value="codIdentificare", required=false) String codIdentificare,
                       @RequestParam(value="cont", required=false) String cont,
                       @RequestParam(value="salariuBrut", required=false) Double salariuBrut,
                       @RequestParam(value="sumaTotala", required=false) Double sumaTotala
                       ) {
        Formular formular = new Formular();
        formular.setNume(nume);
        formular.setPrenume(prenume);
        formular.setInitialaTata(initialaTata);
        formular.setCnp(cnp);
        formular.setAdresa(adresa);
        formular.setJudet(judet);
        formular.setLocalitate(localitate);
        if (telefon == null || telefon == "")
            formular.setTelefon("-");
        else
            formular.setTelefon(telefon);
        if (email == null || email == "")
            formular.setEmail("-");
        else
            formular.setEmail(email);
        formular.setTipVenit(tipVenit);
        formular.setBeneficiar(beneficiar);
        formular.setCodIdentificare(codIdentificare);
        formular.setCont(cont);
        formular.setSalariuBrut(salariuBrut);
        formular.setSumaTotala(sumaTotala);

        try {
            String pdfName = formularService.save(formular);
            pdfKeeper.setPdfName(pdfName);

        } catch (Exception e) {

        }
        return pdfKeeper;
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public @ResponseBody
    PdfKeeper getPdfPath() {
        return pdfKeeper;
    }

    @RequestMapping(value = "/pdfOrder", produces = "application/pdf", method = RequestMethod.GET)
    public ResponseEntity<byte[]> showPdf(@RequestParam(value="pdfName", required=true) String pdfName) {

        String pdfPath = Constants.pdfPath + pdfName;
        System.out.println(pdfPath);
        Path path = Paths.get(pdfPath);

        byte[] pdfContents = null;
        try {
            pdfContents = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline;filename=" + pdfName);

        String filename = pdfName;

        headers.setContentLength(pdfContents.length);
        headers.setContentDispositionFormData(pdfName, pdfName);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                pdfContents, headers, HttpStatus.OK);
        return response;
    }

}

