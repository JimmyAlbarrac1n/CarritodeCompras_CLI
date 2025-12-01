package org.example.service;

import org.example.exception.InsufficientStockException;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona el catálogo y el carrito de forma centralizada.
 */
public class ShoppingCartService {

  private final Map<String, Product> catalog;
  private final ShoppingCart cart;
  private final DiscountService discountService;

  public ShoppingCartService() {
    this.catalog = new HashMap<>();
    this.cart = new ShoppingCart();
    this.discountService = new DiscountService();
    initializeCatalog();
  }

  /**
   * Inicializa el catálogo de productos.
   */
  private void initializeCatalog() {
    addToCatalog(new Product("P001", "Laptop Dell", 1200.00, 10));
    addToCatalog(new Product("P002", "Mouse Logitech", 25.50, 20));
    addToCatalog(new Product("P003", "Teclado Mecánico", 75.00, 15));
    addToCatalog(new Product("P004", "Monitor LG 27\"", 350.00, 8));
  }

  private void addToCatalog(Product product) {
    catalog.put(product.getId(), product);
  }

  /**
   * Obtiene todos los productos del catálogo.
   */
  public List<Product> getAllProducts() {
    return new ArrayList<>(catalog.values());
  }

  /**
   * Obtiene un producto por ID.
   */
  public Product getProduct(String productId) {
    return catalog.get(productId);
  }

  /**
   * Agrega un producto al carrito.
   */
  public void addToCart(String productId, int quantity)
      throws InsufficientStockException {
    Product product = catalog.get(productId);
    if (product == null) {
      throw new IllegalArgumentException("Producto no encontrado");
    }
    cart.addProduct(product, quantity);
  }

  /**
   * Remueve un producto del carrito.
   */
  public boolean removeFromCart(String productId) {
    return cart.removeProduct(productId);
  }

  /**
   * Obtiene el total del carrito.
   */
  public double getCartTotal() {
    return cart.getTotal();
  }

  /**
   * Obtiene el número de items en el carrito.
   */
  public int getCartItemCount() {
    return cart.getItemCount();
  }

  /**
   * Limpia el carrito.
   */
  public void clearCart() {
    cart.clear();
  }

  /**
   * Aplica un cupón de descuento.
   */
  public double applyDiscount(String couponCode) {
    return discountService.applyCoupon(cart.getTotal(), couponCode);
  }

  /**
   * Retorna el estado del carrito como String (para debugging).
   */
  public String getCartSummary() {
    if (cart.isEmpty()) {
      return "Carrito vacío";
    }

    StringBuilder summary = new StringBuilder();
    summary.append("Items en carrito: ").append(cart.getItemCount()).append("\n");
    summary.append("Total: $").append(String.format("%.2f", cart.getTotal()));
    return summary.toString();
  }

  /**
   * Obtiene el carrito actual.
   */
  public ShoppingCart getCart() {
    return cart;
  }
}