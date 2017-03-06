package validatorxsd;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 * Class created for validation XML files against XSD.
 * @author Shumyk
 * @date 2017-03-02
 */
public class ValidatorXSD {

    public static void main(String[] args) {
        //варіант коли через системні файли викликємо
        File xsd = new File("src/resource/checkerStandart.xsd");
        File xml = new File("src/resource/text.xml");
        boolean isValid = validateXML(xsd,xml);
            
        if(isValid){
            System.out.println(" is valid against " );
        } else {
            System.out.println(" is not valid against " );
        }
    }

    private static boolean validateXML(File xsd, File xml) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
        } catch (SAXException | IOException ioe) {
            System.out.println("Exception: " + ioe.getMessage()+"\n source: "+ ioe.toString());
            return false;
        } 
        return true;
    }
    
}
