package ro.ionutmarin.util;


import ro.ionutmarin.entity.FormularEntity;
import ro.ionutmarin.entity.GreetingsEntity;
import ro.ionutmarin.model.Formular;
import ro.ionutmarin.model.Greetings;
import sun.security.util.SecurityConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by ionut on 10/22/2017.
 */
public class XmlConverter {
    public static void modelToXml(FormularEntity formularEntity, String timestamp) {
        String xmlFileName = "formular_" + timestamp + ".xml";

        try {

            File file = new File("C:\\Users\\ionut\\Desktop\\egoverment\\xml\\" + xmlFileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(FormularEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(formularEntity, file);
            jaxbMarshaller.marshal(formularEntity, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
