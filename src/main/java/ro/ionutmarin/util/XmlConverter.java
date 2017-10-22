package ro.ionutmarin.util;


import ro.ionutmarin.entity.GreetingsEntity;
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
    public static void modelToXml(GreetingsEntity greetingsEntity, String timestamp) {
        String xmlFileName = "greetings_" + timestamp + ".xml";

        try {

            File file = new File("C:\\Users\\ionut\\Desktop\\" + xmlFileName);

            JAXBContext jaxbContext = JAXBContext.newInstance(GreetingsEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(greetingsEntity, file);
            jaxbMarshaller.marshal(greetingsEntity, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
