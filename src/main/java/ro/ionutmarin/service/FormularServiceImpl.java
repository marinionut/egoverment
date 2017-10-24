package ro.ionutmarin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ionutmarin.dao.FormularDao;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;
import ro.ionutmarin.model.Formular;
import ro.ionutmarin.model.Greetings;
import ro.ionutmarin.model.TipVenit;
import ro.ionutmarin.util.PdfGenerator;
import ro.ionutmarin.util.XmlConverter;

/**
 * Created by ionut on 10/21/2017.
 */
@Transactional
@Service
public class FormularServiceImpl implements FormularService {
    @Autowired
    private FormularDao dao;

    @Override
    public String save(Formular formular) {
        FormularEntity formularEntity = new FormularEntity();
        formularEntity.setNume(formular.getNume());
        formularEntity.setPrenume(formular.getPrenume());
        formularEntity.setInitialaTata(formular.getInitialaTata());
        formularEntity.setCnp(formular.getCnp());
        formularEntity.setSumaTotala(formular.getSumaTotala());
        formularEntity.setSalariuBrut(formular.getSalariuBrut());
        formularEntity.setAdresa(formular.getAdresa());
        formularEntity.setBeneficiar(formular.getBeneficiar());
        formularEntity.setCodIdentificare(formular.getCodIdentificare());
        formularEntity.setCont(formular.getCont());
        formularEntity.setEmail(formular.getEmail());
        formularEntity.setJudet(formular.getJudet());
        formularEntity.setLocalitate(formular.getLocalitate());
        formularEntity.setTelefon(formular.getTelefon());
        if(formular.getTipVenit() == 0)
            formularEntity.setTipVenit(TipVenit.SALARIU);
        if(formular.getTipVenit() == 1)
            formularEntity.setTipVenit(TipVenit.PENSIE);
        dao.save(formularEntity);

        String timestamp = String.valueOf(System.currentTimeMillis());
        XmlConverter.modelToXml(formularEntity, timestamp);
        String pdfName = PdfGenerator.manipulatePdf(formularEntity, timestamp);

        return pdfName;
    }
}
