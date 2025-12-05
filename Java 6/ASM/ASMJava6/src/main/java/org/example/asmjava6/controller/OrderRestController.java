package org.example.asmjava6.controller;

import org.example.asmjava6.dto.OrderDTO;
import org.example.asmjava6.entity.Account;
import org.example.asmjava6.entity.Order;
import org.example.asmjava6.entity.OrderDetail;
import org.example.asmjava6.entity.Product;
import org.example.asmjava6.repository.AccountDAO;
import org.example.asmjava6.repository.OrderDAO;
import org.example.asmjava6.repository.OrderDetailDAO;
import org.example.asmjava6.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant; // Dùng Instant cho khớp với entity Order
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    AccountDAO accountDAO;

    @PostMapping
    public Order create(@RequestBody OrderDTO orderData) {
        // 1. Tạo đơn hàng (Order)
        Order order = new Order();

        // Tìm tài khoản có username là "user" trong bảng Accounts
        // (Nếu DB bạn chưa có dòng này thì chạy lệnh INSERT mình gửi lúc nãy nhé)
        Account nguoiMua = accountDAO.findById(orderData.getUsername()).orElse(null);

// Kiểm tra lại cho chắc
        if (nguoiMua == null) {
            throw new RuntimeException("Không tìm thấy người dùng: " + orderData.getUsername());
        }

        order.setUsername(nguoiMua);
// Đổi dòng: order.setCreateDate(Instant.now());
// Thành:
        order.setCreateDate(new java.util.Date());
        order.setAddress(orderData.getAddress()); // Lấy địa chỉ từ DTO

        // Lưu Order vào bảng Orders
        Order savedOrder = orderDAO.save(order);

        // 2. Lưu chi tiết đơn hàng vào bảng OrderDetails
        List<OrderDetail> details = new ArrayList<>();

        // Duyệt qua danh sách sản phẩm gửi lên
        for (OrderDTO.OrderDetailDTO item : orderData.getCart()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);

            // Tìm sản phẩm trong bảng Products
            Product product = productDAO.findById(item.getId()).orElse(null);
            if (product != null) {
                detail.setProduct(product);
                detail.setPrice(item.getPrice());
                detail.setQuantity(item.getQty());
                details.add(detail);
            }
        }

        // Lưu tất cả chi tiết
        orderDetailDAO.saveAll(details);

        return savedOrder;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderDAO.findAll();
    }

    // 2. User: Lấy đơn hàng của mình
    @GetMapping("/user/{username}")
    public List<Order> getByUser(@PathVariable("username") String username) {
        return orderDAO.findByUsername_Username(username);
    }
}