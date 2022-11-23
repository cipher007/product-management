package ae.adib.mstask.productmanagement.app.service;

import ae.adib.mstask.productmanagement.app.domain.model.Product;
import ae.adib.mstask.productmanagement.app.domain.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product saveOrUpdate(Product product) {
        LOGGER.info("Saving...");
        return productRepository.save(product);
    }

    public Product saveOrUpdate(Long productId, Product product) {
        Optional<Product> findById = productRepository.findById(productId);

        if (!findById.isPresent()) {
            throw new EntityNotFoundException(String.format("Entity id %s not found", product.toString()));
        }

        Product updatable = findById.get();

        updatable.setName(product.getName());
        updatable.setDescription(product.getDescription());
        updatable.setPrice(product.getPrice());

        LOGGER.info("Updating...");

        return productRepository.save(updatable);
    }

    public void delete(Long productId) {
        LOGGER.info("Deleting...");
        productRepository.deleteById(productId);
    }

    public List<Product> retrieveProducts() {
        List<Product> allProducts = productRepository.findAll();

        return allProducts;
    }

    public Product retrieveProduct(Long productId) {
        Optional<Product> findById = productRepository.findById(productId);

        if (!findById.isPresent()) {
            return null;
        }

        Product product = findById.get();
        return product;
    }

}
