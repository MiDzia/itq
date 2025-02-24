package by.walker.orders.model;

import java.sql.Timestamp;
import java.util.Objects;


public class Order {

    private Long id;
    private String numberOfOrder;
    private Long totalAmount;
    private Timestamp date;
    private String recipient;
    private String deliveryAddress;
    private TypeOfPayment typeOfPayment;
    private TypeOfDelivery typeOfDelivery;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberOfOrder() {
        return numberOfOrder;
    }

    public void setNumberOfOrder(String numberOfOrder) {
        this.numberOfOrder = numberOfOrder;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public TypeOfPayment getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(TypeOfPayment typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public TypeOfDelivery getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(TypeOfDelivery typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }

    public Order(Long id, String numberOfOrder, Long totalAmount, Timestamp date, String recipient,
        String deliveryAddress,
        TypeOfPayment typeOfPayment, TypeOfDelivery typeOfDelivery) {
        this.id = id;
        this.numberOfOrder = numberOfOrder;
        this.totalAmount = totalAmount;
        this.date = date;
        this.recipient = recipient;
        this.deliveryAddress = deliveryAddress;
        this.typeOfPayment = typeOfPayment;
        this.typeOfDelivery = typeOfDelivery;
    }

    public Order() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) {
            return false;
        }
        return Objects.equals(id, order.id) && Objects.equals(numberOfOrder, order.numberOfOrder)
               && Objects.equals(totalAmount, order.totalAmount) && Objects.equals(date, order.date)
               && Objects.equals(recipient, order.recipient) && Objects.equals(deliveryAddress,
            order.deliveryAddress) && typeOfPayment == order.typeOfPayment && typeOfDelivery == order.typeOfDelivery;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfOrder, totalAmount, date, recipient, deliveryAddress, typeOfPayment,
            typeOfDelivery);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", numberOfOrder='" + numberOfOrder + '\'' +
               ", totalAmount=" + totalAmount +
               ", date=" + date +
               ", recipient='" + recipient + '\'' +
               ", deliveryAddress='" + deliveryAddress + '\'' +
               ", typeOfPayment=" + typeOfPayment +
               ", typeOfDelivery=" + typeOfDelivery +
               '}';
    }
}
