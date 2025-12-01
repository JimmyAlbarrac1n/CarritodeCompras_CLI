package org.example.ui;

import org.example.model.CartItem;
import org.example.model.Product;

import java.util.List;

/**
 * Responsable de mostrar el contenido del carrito de compras.
 */
public class CartView {

  /**
   * Muestra el contenido completo del carrito.
   */
  public void displayCart(List<CartItem> items, int itemCount, int totalProducts, double total) {
    System.out.println("\n🛒 CONTENIDO DEL CARRITO");
    System.out.println("═════════════════════════════════════════════════════════════");

    if (items.isEmpty()) {
      System.out.println("El carrito está vacío.\n");
      return;
    }

    System.out.printf("%-6s │ %-30s │ %-8s │ %-10s │ %-10s%n",
        "ID", "Producto", "Cantidad", "Precio", "Subtotal");
    System.out.println("─────────────────────────────────────────────────────────────");

    for (CartItem item : items) {
      Product product = item.getProduct();
      System.out.printf("%-6s │ %-30s │ %-8d │ $%-9.2f │ $%-9.2f%n",
          product.getId(),
          product.getName(),
          item.getQuantity(),
          product.getPrice(),
          item.getSubtotal()
      );
    }

    System.out.println("─────────────────────────────────────────────────────────────");
    System.out.printf("Total de items: %d │ Total de productos: %d │ TOTAL: $%.2f%n",
        itemCount, totalProducts, total);
    System.out.println("═════════════════════════════════════════════════════════════\n");
  }

  /**
   * Muestra el resumen de descuento aplicado.
   */
  public void displayDiscount(double original, double discount, double finalTotal) {
    System.out.printf("\n✅ Descuento aplicado: $%.2f (%.1f%%)%n",
        discount, (discount / original) * 100);
    System.out.printf("💰 Total final: $%.2f%n\n", finalTotal);
  }
}