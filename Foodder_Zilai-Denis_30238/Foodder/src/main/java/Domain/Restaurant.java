package Domain;

import java.util.Objects;

public class Restaurant extends Entity<Long>{
    private String name;
    private String specific;

    public Restaurant(String name, String specific) {
        this.name = name;
        this.specific = specific;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecific() {
        return specific;
    }

    public void setSpecific(String specific) {
        this.specific = specific;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name) && Objects.equals(specific, that.specific);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specific);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", specific='" + specific + '\'' +
                '}';
    }

    public Restaurant() {
    }
}
