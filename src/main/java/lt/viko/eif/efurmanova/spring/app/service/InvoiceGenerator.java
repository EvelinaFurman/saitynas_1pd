package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface InvoiceGenerator {
    Invoice generate (Order order);
}
