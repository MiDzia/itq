package by.walker.orders.model;

import java.util.Objects;

public class OrderDetails {

    private Long id;
    private Integer article;
    private String name;
    private Integer amount;
    private Long unitPrice;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.article = article;
        this.name = name;
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
        return Objects.equals(id, that.id) && Objects.equals(article, that.article)
               && Objects.equals(name, that.name) && Objects.equals(amount, that.amount)
               && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, name, amount, unitPrice, orderId);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
               "id=" + id +
               ", article=" + article +
               ", name='" + name + '\'' +
               ", amount=" + amount +
               ", unitPrice=" + unitPrice +
               ", orderId=" + orderId +
               '}';
    }
}
