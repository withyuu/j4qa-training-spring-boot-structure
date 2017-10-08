package com.ascend.training.j4qa.structure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity // This tells Hibernate to make a table out of this class
public class Toy {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    private String name;

    public Toy() {}

    public Toy(String brand, String model, String name) {
        this.brand = brand;
        this.model = model;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toy)) return false;

        Toy toy = (Toy) o;

        if (!id.equals(toy.id)) return false;
        if (!brand.equals(toy.brand)) return false;
        if (!model.equals(toy.model)) return false;
        return name != null ? name.equals(toy.name) : toy.name == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
