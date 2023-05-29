package Domain;

import java.util.Objects;

public class Order extends Entity<Long>{
    private Integer orderNo;
    private float price;
    private int quantity;
    private boolean status;

    public Order(Integer orderNo, float price, int quantity, boolean status) {
        this.orderNo = orderNo;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.price, price) == 0 && quantity == order.quantity && status == order.status && Objects.equals(orderNo, order.orderNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo, price, quantity, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }

    public Order() {
    }
}
