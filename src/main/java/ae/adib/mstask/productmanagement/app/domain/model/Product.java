package ae.adib.mstask.productmanagement.app.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * the Product model where properties exist
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /*
     * set and assign datetime value upon update operation
     */
    @PreUpdate
    public void onPreUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * set and assign datetime value to new created product
     */
    @PrePersist
    public void onPrePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.setUpdatedAt(now);
        this.setCreatedAt(now);
    }

    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
