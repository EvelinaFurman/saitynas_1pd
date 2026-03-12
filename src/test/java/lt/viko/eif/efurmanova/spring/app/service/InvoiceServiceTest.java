package lt.viko.eif.efurmanova.spring.app.service;

import lt.viko.eif.efurmanova.spring.app.domain.model.Customer;
import lt.viko.eif.efurmanova.spring.app.domain.model.Invoice;
import lt.viko.eif.efurmanova.spring.app.domain.model.Item;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceGenerator invoiceGenerator;

    @Test
    public void shouldGenerateInvoiceFromOrder() {

        //given
        Item item1 = new Item("Laptop", 2, 10.0);
        Item item2 = new Item("Mouse", 3, 5.0);

        Order order = new Order(1L,
                "2026-02-01",
                "2026-02-08",
                "NEW",
                new Customer("Jonas Jonaitis", "Green street 455", "+3706000000", "jonas.jonaitis@gmail.com"),
                List.of(item1, item2));
        double expectedTotal = order.getTotalAmount();

        //when
        Invoice invoice = invoiceGenerator.generate(order);

        //then
        assertNotNull(invoice);
        assertEquals(order.getId(), invoice.getInvoiceNumber());
        assertEquals(order.getCustomer(), invoice.getCustomer());
        assertEquals(order.getItems(), invoice.getItems());

        assertEquals(expectedTotal, invoice.getAmount());
    }

}

