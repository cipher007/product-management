package ae.adib.mstask.productmanagement.app.controller.rest;

import ae.adib.mstask.productmanagement.app.controller.adapter.ProductAdapter;
import ae.adib.mstask.productmanagement.app.controller.dto.ProductDTO;
import ae.adib.mstask.productmanagement.app.domain.model.Product;
import ae.adib.mstask.productmanagement.app.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller layer
 * exposes our services to endpoint and client though defined urls and HTTP methods
 * POST method invoked and product saving (insert new product)
 * <B>/{productId} with PUT method: </B> operation where you update the product with given id
 * <B>/{productId} with DELETE method: </B> operation where you delete the product with given id
 * <B>/list with GET method: </B> operation where you display all products stored in DB
 * <B>/productId/list with GET method: </B> operation where you display specific products for given id
 */
@Controller
public class ProductRestController {

    @Autowired
    private ProductService productService;

    
    @RestController
    @RequestMapping(value = "/product")
    @Tag(name = "Product Manager API - Product", description = "Product operations.")
    public class ProductRest {

        /**
         * Insert new product operation
         * 
         * @param dto
         * @return http status for created product of failure
         */
        @Operation(summary = "Create Product", description = "Create a new product.")
        @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> create(@Valid @RequestBody ProductDTO dto) {
            Product product = new ProductAdapter(dto).convertToEntity();

            productService.saveOrUpdate(product);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        /**
         * update existing product with provided id
         * 
         * @param productId
         * @param dto
         * @return Http response with OK for successful update
         */
        @Operation(summary = "Update Product", description = "Update specific product that you provide id for")
        @RequestMapping(method = RequestMethod.PUT, value = "/{productId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> update(@PathVariable("productId") long productId,
                @Valid @RequestBody ProductDTO dto) {

            Product product = new ProductAdapter(dto).convertToEntity();

            productService.saveOrUpdate(productId, product);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        /**
         * Delete provided product id
         * 
         * @param productId
         * @return http response accepted when deleted
         */
        @Operation(summary = "Delete Product", description = "Delete a specific product with provided id")
        @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> delete(@PathVariable("productId") long productId) {

            productService.delete(productId);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }

        /**
         * Retrieves all products to endpoint in JSON format
         * @return products records
         */
        @Operation(summary = "Retrieve All Products", description = "Get all products listed")
        @RequestMapping(method = RequestMethod.GET, value = "/list", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<List<ProductDTO>> requestAllProduct() {

            List<Product> products = productService.retrieveProducts();

            ProductAdapter adapter = new ProductAdapter(products);
            List<ProductDTO> productDTOList = adapter.fromEntityList();

            return new ResponseEntity<>(productDTOList, HttpStatus.OK);
        }

        /**
         * Retrieves specific product for given id in JSON format
         * @param productId
         * @return designated product
         */
        @Operation(summary = "Retrieve a specific Single Products", description = "Get a specific product")
        @RequestMapping(method = RequestMethod.GET, value = "{productId}/list", produces = APPLICATION_JSON_VALUE)
        public ResponseEntity<ProductDTO> requestSpecificEntity(@PathVariable("productId") long productId) {

            Product product = productService.retrieveProduct(productId);

            ProductAdapter adapter = new ProductAdapter(product);
            ProductDTO productDTO = adapter.convertToDTO();

            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        }
    }

}