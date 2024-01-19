package org.learning.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cocktails")
public class Cocktail {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID CON AUTO-INCREMENT
    private Integer id;
    @NotEmpty
    @Column(nullable = false)
    private String name;
    private String description;
    private String image;
    @Column(nullable = false)
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @ManyToOne
    private List<Category> categories;

    // GETTER & SETTER

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
