package by.walker.orders.model;

import java.util.Objects;

public class OrderDetails {

    private Long id;
    private Integer productArticle;
    private String productName;
    private Integer amount;
    private Long unitPrice;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductArticle() {
        return productArticle;
    }

    public void setProductArticle(Integer productArticle) {
        this.productArticle = productArticle;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderDetails(Long id, Integer article, String name, Integer amount, Long unitPrice, Long orderId) {
        this.id = id;
        this.productArticle = article;
        this.productName = name;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.orderId = orderId;
    }

    public OrderDetails() {
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDetails that)) {
            return false;
        }
        return Objects.equals(id, that.id) && Objects.equals(productArticle, that.productArticle)
               && Objects.equals(productName, that.productName) && Objects.equals(amount, that.amount)
               && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productArticle, productName, amount, unitPrice, orderId);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
               "id=" + id +
               ", article=" + productArticle +
               ", name='" + productName + '\'' +
               ", amount=" + amount +
               ", unitPrice=" + unitPrice +
               ", orderId=" + orderId +
               '}';
    }
}
