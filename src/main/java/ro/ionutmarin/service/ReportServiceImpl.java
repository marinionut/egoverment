package ro.ionutmarin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ionutmarin.dao.FormularDao;
import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.model.Person;
import ro.ionutmarin.model.Report;
import ro.ionutmarin.model.TipVenit;
import ro.ionutmarin.util.PdfGenerator;
import ro.ionutmarin.util.ReportGenerator;
import ro.ionutmarin.util.XmlConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ionut on 11/18/2017.
 */
@Transactional
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private FormularDao dao;

    @Override
    public String getReport() {
        List<Report> reportList = getReportDataFromFormular();

        String pdfReportName = ReportGenerator.getReportPath(reportList);

        return pdfReportName;
    }

    private List<Report> getReportDataFromFormular() {
        List<FormularEntity> formularEntityList = dao.findAllFormular();
        List<Report> reportList = new ArrayList<>();

        Map<String, List<FormularEntity>> map = new HashMap<String, List<FormularEntity>>();

        for (FormularEntity formularEntity : formularEntityList) {
            String key  = formularEntity.getJudet();
            if(map.containsKey(key)){
                List<FormularEntity> list = map.get(key);
                list.add(formularEntity);

            }else{
                List<FormularEntity> list = new ArrayList<FormularEntity>();
                list.add(formularEntity);
                map.put(key, list);
            }
        }


        for (Map.Entry<String, List<FormularEntity>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue().toString());

            List<Person> personList = new ArrayList<>();

            Report report = new Report();
            report.setJudet(entry.getKey());

            Double medieSalariu = 0.0;
            for(FormularEntity formularEntity : entry.getValue()) {
                if (formularEntity.getCnp().startsWith("1")) {
                    report.setNumarBarbatiJudet(report.getNumarBarbatiJudet() + 1);
                } else {
                    report.setNumarFemeiJudet(report.getNumarFemeiJudet() + 1);
                }

                if (formularEntity.getTipVenit() == TipVenit.PENSIE) {
                    report.setNumarVenitPensie(report.getNumarVenitPensie() + 1);
                } else {
                    report.setNumarVenitSalariu(report.getNumarVenitSalariu() + 1);
                }

                medieSalariu = formularEntity.getSalariuBrut();

                Person person = new Person();
                person.setNume(formularEntity.getNume());
                person.setPrenume(formularEntity.getPrenume());
                person.setInitialaTata(formularEntity.getInitialaTata());
                person.setCnp(formularEntity.getCnp());
                person.setEmail(formularEntity.getEmail());
                person.setTelefon(formularEntity.getTelefon());
                personList.add(person);
            }

            medieSalariu = medieSalariu/entry.getValue().size();
            report.setMedieVenitJudet(medieSalariu);
            report.setPersons(personList);

            reportList.add(report);
        }
        return reportList;
    }
}
