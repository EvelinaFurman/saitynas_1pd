package lt.viko.eif.efurmanova.spring.app.domain.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {


    @XmlElement(name = "item")
    private List<Item> items;
    private double amount;
    private Customer customer;
    private Long invoiceNumber;

    public Invoice(
            Long invoiceNumber,
            Double amount,
            List<Item> items,
            Customer customer
    ) {
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.items = items;
        this.customer = customer;
    }

    public Invoice() {
    }

    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
