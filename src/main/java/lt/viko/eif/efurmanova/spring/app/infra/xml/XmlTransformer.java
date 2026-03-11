package lt.viko.eif.efurmanova.spring.app.infra.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.springframework.stereotype.Component;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XmlTransformer {

    private final Schema schema;

    public XmlTransformer() {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            this.schema = sf.newSchema(getClass().getClassLoader().getResource("order.xsd"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Order transformToPojo(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Order.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setSchema(schema);
            return (Order) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String transformToXml(Invoice invoice) {
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(invoice, sw);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}