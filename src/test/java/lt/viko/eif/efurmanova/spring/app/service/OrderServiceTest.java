package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Customer;
import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Item;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import lt.viko.eif.efurmanova.spring.app.infra.xml.XmlTransformer;
import lt.viko.eif.efurmanova.spring.app.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    String correctXml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE order SYSTEM "order.dtd">
            <order xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="order.xsd">
                <customer>
                    <name>Jonas Jonaitis</name>
                    <address>Green street 455</address>
                    <phoneNumber>+3706000000</phoneNumber>
                    <emailAddress>jonas.jonaitis@gmail.com</emailAddress>
                </customer>
                <invoiceNumber>INV-1001</invoiceNumber>
                <orderDate>2026-02-01</orderDate>
                <deliveryDate>2026-02-08</deliveryDate>
                <status>NEW</status>
                <items>
                    <item>
                        <product>Computer mouse</product>
                        <quantity>1</quantity>
                        <price>25.99</price>
                    </item>
                </items>
            </order>""";
    String correctInvoiceXml = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <invoice>
                <item>
                    <product>Computer mouse</product>
                    <quantity>1</quantity>
                    <price>25.99</price>
                </item>
                <amount>586.95</amount>
                <customer>
                    <name>Jonas Jonaitis</name>
                    <address>Green street 455</address>
                    <phoneNumber>+3706000000</phoneNumber>
                    <emailAddress>jonas.jonaitis@gmail.com</emailAddress>
                </customer>
                <invoiceNumber>1</invoiceNumber>
            </invoice>""";
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private XmlTransformer transformer;
    @Mock
    private InvoiceService invoiceService;
    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldProcessOrder() {
        //given
        Order order = new Order(
                null,
                "2026-02-01",
                "2026-02-08",
                "NEW",
                new Customer("Jonas Jonaitis", "Green street 455", "+3706000000", "jonas.jonaitis@gmail.com"),
                List.of(new Item("Computer mouse", 1, 25.99))
        );

        Order savedOrder = new Order(
                1L,
                "2026-02-01",
                "2026-02-08",
                "NEW",
                new Customer("Jonas Jonaitis", "Green street 455", "+3706000000", "jonas.jonaitis@gmail.com"),
                List.of(new Item("Computer mouse", 1, 25.99))
        );

        Invoice invoice = new Invoice(
                1L,
                586.95,
                List.of(new Item("Computer mouse", 1, 25.99)),
                new Customer(
                        "Jonas Jonaitis",
                        "Green street 455",
                        "+3706000000",
                        "jonas.jonaitis@gmail.com"
                )
        );

        when(transformer.transformToPojo(correctXml)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
        when(invoiceService.generateInvoice(savedOrder)).thenReturn(invoice);
        when(transformer.transformToXml(invoice)).thenReturn(correctInvoiceXml);

        //when
        String result = orderService.processOrder(correctXml);

        //then
        assertEquals(correctInvoiceXml, result);
    }
}
