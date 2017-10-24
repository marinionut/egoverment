package ro.ionutmarin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ionutmarin.model.Formular;
import ro.ionutmarin.model.Greetings;
import ro.ionutmarin.model.TipVenit;
import ro.ionutmarin.service.FormularService;
import ro.ionutmarin.util.PdfKeeper;

import java.io.File;
import java.io.IOException;
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
                       @RequestParam(value="telefon", required=false) String telefon,
                       @RequestParam(value="email", required=false) String email,
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
        formular.setTelefon(telefon);
        formular.setEmail(email);
        formular.setTipVenit(tipVenit);
        formular.setBeneficiar(beneficiar);
        formular.setCodIdentificare(codIdentificare);
        formular.setCont(cont);
        formular.setSalariuBrut(salariuBrut);
        formular.setSumaTotala(sumaTotala);

        try {
            String absolutePathPdf = formularService.save(formular);
            pdfKeeper.setAbsolutePath(absolutePathPdf);

        } catch (Exception e) {

        }
        return pdfKeeper;
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public @ResponseBody
    PdfKeeper getPdfPath() {
        return pdfKeeper;
    }

}

