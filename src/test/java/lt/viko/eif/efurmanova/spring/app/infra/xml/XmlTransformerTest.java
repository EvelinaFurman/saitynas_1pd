package lt.viko.eif.efurmanova.spring.app.infra.xml;

import lt.viko.eif.efurmanova.spring.app.domain.model.Customer;
import lt.viko.eif.efurmanova.spring.app.domain.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class XmlTransformerTest {
    private final XmlOrderTransformer xmlTransformer = new XmlOrderTransformer();
    @Test
    public void shouldTransformToPojo(){
        //given
    String xml = """
                <order>
                    <customer>
                        <name>Jonas Jonaitis</name>
                        <address>Green street 455</address>
                            <phoneNumber>+3706000000</phoneNumber>
                            <emailAddress>jonas.jonaitis@gmail.com</emailAddress>
                    </customer>
                    <items>
                        <item>
                            <product>Laptop</product>
                            <quantity>2</quantity>
                            <price>10.0</price>
                        </item>
                        <item>
                            <product>Mouse</product>
                            <quantity>3</quantity>
                            <price>5.0</price>
                        </item>
                    </items>
                </order>
                """;

        Order order = xmlTransformer.transformToPojo(xml);

        assertNotNull(order);
        assertNotNull(order.getCustomer());
        assertEquals("Jonas Jonaitis", order.getCustomer().getName());
        assertEquals("Green street 455", order.getCustomer().getAddress());
        assertEquals("+3706000000", order.getCustomer().getPhoneNumber());
        assertEquals("jonas.jonaitis@gmail.com", order.getCustomer().getEmailAddress());
        assertEquals(2, order.getItems().size());
        assertEquals(35.0, order.getTotalAmount());



    }
}
