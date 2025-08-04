package Repository;

import Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.category = ?1")
    List<Product> findByCategory(String category);
    @Query("select p from Product p where p.quantityInStock < ?1")
    List<Product> findByQuantityInStockLessThan(Integer quantity);
    @Query("""
            select p from Product p
            where p.productName like concat('%', ?1, '%') or p.description like concat('%', ?2, '%')""")
    List<Product> findByProductNameContainingOrDescriptionContaining(String kw1, String kw2);
    @Query("select p from Product p order by p.expiryDate")
    List<Product> findAllByOrderByExpiryDateAsc();

}