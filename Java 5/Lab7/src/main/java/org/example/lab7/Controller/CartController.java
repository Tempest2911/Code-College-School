package org.example.lab7.Controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Map<Integer, Integer> cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("products", session.getAttribute("products"));
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable int id, HttpSession session) {
        Map<Integer, Integer> cart = getCart(session);
        cart.put(id, cart.getOrDefault(id, 0) + 1);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable int id,
                                 @RequestParam int quantity,
                                 HttpSession session) {
        Map<Integer, Integer> cart = getCart(session);
        if (quantity <= 0) cart.remove(id);
        else cart.put(id, quantity);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable int id, HttpSession session) {
        getCart(session).remove(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    private Map<Integer, Integer> getCart(HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
