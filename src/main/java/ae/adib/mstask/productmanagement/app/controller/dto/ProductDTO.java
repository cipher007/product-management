package ae.adib.mstask.productmanagement.app.controller.dto;

import java.util.Set;

import ae.adib.mstask.productmanagement.app.domain.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductDTO {

    @XmlAttribute
    @Schema(name = "Product id", hidden = true)
    private Long id;

    @XmlAttribute
    @Schema(name = "Product name", example = "Phone")
    private String name;

    @XmlAttribute
    @Schema(name = "Product description", example = "Samsung phone")
    private String description;

    @XmlAttribute(name = "price")
    @Schema(name = "Price")
    private Double price;

    @XmlAttribute(name = "images")
    @Schema(name = "Products")
    private Set<ProductDTO> products;

    public Long getId() {
        return id;
    }

    public void setId(Long i) {
        this.id = i;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product toEntity() {
        Product entity = new Product();

        entity.setName(this.name);
        entity.setDescription(this.description);
        entity.setPrice(this.price);

        return entity;
    }

    public void addProductDTO(ProductDTO productDTO) {
        if (productDTO != null) {
            this.products.add(productDTO);
        }
    }

}
