package Service;

import Model.Product;
import Repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getLowStockProducts() {
        return productRepository.findByQuantityInStockLessThan(500);
    }

    public List<Product> getProductsSortedByExpiry() {
        return productRepository.findAllByOrderByExpiryDateAsc();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByProductNameContainingOrDescriptionContaining(keyword, keyword);
    }
}
