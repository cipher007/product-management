package ae.adib.mstask.productmanagement.app.domain.repository;

import ae.adib.mstask.productmanagement.app.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Product repository layer using JPA 
 * we can add custom queries bases on our needs as special implementation
 * 
 * This layer connects our application and service layer to the DB and data store 
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> findAll();

    @Query("select p from Product p where p.id = ?1")
    Optional<Product> findById(Long productId);

}
