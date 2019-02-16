package utility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Marshaller {

    public static Object unmarshall(Class T, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(T);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
