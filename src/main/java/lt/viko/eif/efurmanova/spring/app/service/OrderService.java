package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import lt.viko.eif.efurmanova.spring.app.infra.xml.XmlTransformer;
import lt.viko.eif.efurmanova.spring.app.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InvoiceService invoiceService;
    private final XmlTransformer transformer;

    public OrderService(OrderRepository orderRepository, InvoiceService invoiceService, XmlTransformer transformer) {
        this.orderRepository = orderRepository;
        this.invoiceService = invoiceService;
        this.transformer = transformer;
    }

    public String processOrder(String xml) {
        Order order = transformer.transformToPojo(xml);
        System.out.println(order);
        Order orderFromDb = orderRepository.save(order);
        Invoice invoice = invoiceService.generateInvoice(orderFromDb);

        return transformer.transformToXml(invoice);
    }
}