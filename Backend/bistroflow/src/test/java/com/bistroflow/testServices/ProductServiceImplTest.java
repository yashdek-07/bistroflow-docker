package com.bistroflow.testServices;

import com.bistroflow.model.Product;
import com.bistroflow.repository.ProductRepo;
import com.bistroflow.service.product.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// if we want to test Service then we will Inject Mock on service and Mock repo
// to test controller we INJECT MOCK on Controller and mock Service


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetAllProduct() {

        Product product1 = new Product(1, "Laptop", "Gaming Laptop", 1200.00, null);
        Product product2 = new Product(2, "Headphones", "Noise Cancelling", 250.00, null);
        when(productRepo.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProduct();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Headphones", products.get(0).getProductName());

    }

    @Test
    void testGetProductById() {

        Product product = new Product(1, "Laptop", "Gaming Laptop", 1200.00, null);

        when(productRepo.findById(1)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1);

        // Assert
        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getProductName());

    }
}
