package Domain;

import java.util.Objects;

public class Transporter extends Entity<Long>{
    private String name;

    public Transporter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transporter that = (Transporter) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Transporter{" +
                "name='" + name + '\'' +
                '}';
    }

    public Transporter() {
    }
}
