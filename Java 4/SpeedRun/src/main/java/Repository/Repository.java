package Repository;

import Model.Product;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Repository {

    // 1. Lấy toàn bộ sản phẩm
    public List<Product> getAllProduct() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.getResultList();
        }
    }

    // 2. Lấy sản phẩm có category là "Đồ uống"
    public List<Product> getDrinks() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product p WHERE p.category = :category", Product.class);
            query.setParameter("category", "Đồ uống");
            return query.getResultList();
        }
    }

    // 3. Sản phẩm sắp hết hàng
    public List<Product> getOutOfStockProducts() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product p WHERE p.quantityInStock < 500", Product.class);
            return query.getResultList();
        }
    }

    // 4. Sản phẩm sắp hết hạn (theo ExpiryDate tăng dần)
    public List<Product> getProductsByExpiryDate() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product p ORDER BY p.expiryDate ASC", Product.class);
            return query.getResultList();
        }
    }

    // 5. Tìm kiếm sản phẩm theo từ khóa
    public List<Product> searchProducts(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product p WHERE p.productName LIKE :kw OR p.description LIKE :kw", Product.class);
            query.setParameter("kw", "%" + keyword + "%");
            return query.getResultList();
        }
    }
    
}