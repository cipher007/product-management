package ae.adib.mstask.productmanagement.app.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ae.adib.mstask.productmanagement.app.domain.model.Product;
import ae.adib.mstask.productmanagement.app.domain.repository.ProductRepository;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Create Product Test ")
    void createUserTest() {

        Product created = productRepository.save(getProduct());

        assertTrue(created != null);
    }

    private Product getProduct() {

        Product product = new Product();
        product.setId(3L);
        product.setName("camera");
        product.setDescription("DSLR Camera with auto focus");
        product.setPrice(500.00);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }
}
