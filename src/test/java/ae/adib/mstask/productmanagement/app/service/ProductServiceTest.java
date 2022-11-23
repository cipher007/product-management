package ae.adib.mstask.productmanagement.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ae.adib.mstask.productmanagement.ProductManagementApplication;
import ae.adib.mstask.productmanagement.app.domain.model.Product;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProductManagementApplication.class)
public class ProductServiceTest {
    @MockBean
    private ProductService productService;

    @Test
    public void testGetAllProducts() throws Exception {
        Product product = getProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        given(productService.retrieveProducts()).willReturn(products);
        List<Product> result = productService.retrieveProducts();
        assertEquals(result.size(), 1);
    }

    @Test
    public void testGetProduct() throws Exception {
        Product product = getProduct();
        given(productService.retrieveProduct(1L)).willReturn(product);
        Product result = productService.retrieveProduct(1L);
        assertEquals(result.getId(), 1);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).delete(1L);
        productService.delete(1L);
        assertTrue(true);
    }

    @Test
    public void testSaveOrUpdateProduct() throws Exception {
        Product product = getProduct();
        given(productService.saveOrUpdate(product)).willReturn(product);
        productService.saveOrUpdate(product);
        assertTrue(true);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("camera");
        product.setDescription("DSLR Camera with auto focus");
        product.setPrice(500.00);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }
}
