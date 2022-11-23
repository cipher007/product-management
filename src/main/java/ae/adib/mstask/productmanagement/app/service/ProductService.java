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

/**
 * This class is the service layer of the product
 * where it exposes many functionality to the client like save, update, delete,
 * or list product
 * it is the layer between the controller and repository for better isolation
 * and separation of specialty
 * 
 * @see Product
 * @see ProductRestController
 * @see ProductRepository
 */
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

    /**
     * Save/insert and new product or update existing product with provided product
     * identification
     * 
     * @param productId
     * @param product
     * @return the newly inserted or update product
     */
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

    /**
     * delete product record for provided product id
     * 
     * @param productId
     */
    public void delete(Long productId) {
        LOGGER.info("Deleting...");
        productRepository.deleteById(productId);
    }

    /**
     * Retrieves products functionality
     * 
     * @return list of product exist in DB
     */
    public List<Product> retrieveProducts() {
        List<Product> allProducts = productRepository.findAll();

        return allProducts;
    }

    /**
     * retrieve specific product using product id
     * 
     * @param productId
     * @return Product
     */
    public Product retrieveProduct(Long productId) {
        Optional<Product> findById = productRepository.findById(productId);

        if (!findById.isPresent()) {
            return null;
        }

        Product product = findById.get();
        return product;
    }

}
