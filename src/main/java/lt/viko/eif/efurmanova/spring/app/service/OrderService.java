package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Item;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import lt.viko.eif.efurmanova.spring.app.infra.xml.XmlReader;
import lt.viko.eif.efurmanova.spring.app.infra.xml.XmlWriter;
import lt.viko.eif.efurmanova.spring.app.repository.OrderRepository;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final XmlReader<Order> orderXmlConverter;
    private final XmlWriter<Invoice> invoiceXmlConverter;
    private final InvoiceGenerator invoiceGenerator;

    public OrderService(OrderRepository orderRepository, XmlReader<Order> orderXmlConverter, XmlWriter<Invoice> invoiceXmlConverter1, InvoiceGenerator invoiceGenerator) {
        this.orderRepository = orderRepository;
        this.orderXmlConverter = orderXmlConverter;
        this.invoiceXmlConverter = invoiceXmlConverter1;
        this.invoiceGenerator = invoiceGenerator;
    }

    public String processOrder(String xml) {
        Order order = orderXmlConverter.transformToPojo(xml);
        System.out.println(order);
        for (Item item : order.getItems()) {
            item.setOrder(order);
        };
        Order orderFromDb = orderRepository.save(order);
        Invoice invoice = invoiceGenerator.generate(orderFromDb);

        return invoiceXmlConverter.transformToXml(invoice);
    }
}