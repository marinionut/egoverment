package ro.ionutmarin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ionutmarin.dao.GreetingsDao;
import ro.ionutmarin.entity.GreetingsEntity;
import ro.ionutmarin.model.Greetings;
import ro.ionutmarin.util.PdfGenerator;
import ro.ionutmarin.util.XmlConverter;

/**
 * Created by ionut on 10/21/2017.
 */
@Transactional
@Service
public class GreetingsServiceImpl implements GreetingsService{
    @Autowired
    private GreetingsDao dao;

    @Override
    public void save(Greetings greetings) {
        GreetingsEntity greetingsEntity = new GreetingsEntity();
        greetingsEntity.setContent(greetings.getContent());
        dao.save(greetingsEntity);

        String timestamp = String.valueOf(System.currentTimeMillis());
        XmlConverter.modelToXml(greetingsEntity, timestamp);
        PdfGenerator.manipulatePdf(greetingsEntity, timestamp);
    }
}
