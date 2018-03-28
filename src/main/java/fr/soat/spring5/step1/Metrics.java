package fr.soat.spring5.step1;

import java.time.Instant;
import java.util.Objects;

public class Metrics {

    /**
     * Heure de création de la metrique (optionnel)
     */
    private Instant instant;

    /**
     * Nom de la métrique
     */
    private String name;

    /**
     * Valeur de la métrique
     */
    private String value;


    public Metrics() {
    }

    public Metrics(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metrics metrics = (Metrics) o;
        return Objects.equals(name, metrics.name) &&
                Objects.equals(value, metrics.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "instant=" + instant +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
