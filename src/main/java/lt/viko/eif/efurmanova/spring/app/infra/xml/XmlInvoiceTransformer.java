package lt.viko.eif.efurmanova.spring.app.infra.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

@Component
public class XmlInvoiceTransformer implements XmlWriter<Invoice> {

    @Override
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