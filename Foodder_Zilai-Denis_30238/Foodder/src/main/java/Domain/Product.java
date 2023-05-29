package Domain;

import java.util.Objects;

public class Product extends Entity<Long>{
    private String name;
    private float price;
    private int quantity;
    private Integer restoId;

    public Product(String name, float price, int quantity, Integer restoId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.restoId = restoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getRestoId() {
        return restoId;
    }

    public void setRestoId(Integer restoId) {
        this.restoId = restoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(name, product.name) && Objects.equals(restoId, product.restoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, restoId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", restoId=" + restoId +
                '}';
    }

    public Product() {
    }
}
