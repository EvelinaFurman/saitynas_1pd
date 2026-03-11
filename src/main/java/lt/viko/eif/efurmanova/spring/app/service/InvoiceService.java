package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    public InvoiceService() {
    }

    public Invoice generateInvoice(Order order) {
        return new Invoice(
                order.getId(),
                order.getTotalAmount(),
                order.getItems(),
                order.getCustomer()
        );
    }
}
