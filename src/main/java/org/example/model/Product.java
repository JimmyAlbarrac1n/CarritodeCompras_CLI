package org.example.model;

import java.util.Objects;
import java.util.List;

/**
 * Representa un producto en la tienda.
 * Contiene información sobre el producto y su disponibilidad en stock.
 */
public class Product {
  private final String id;
  private final String name;
  private final double price;
  private int stock;

  private int unusedCounter;

  /**
   * Constructor de Product.
   *
   * @param id    Identificador único del producto
   * @param name  Nombre del producto
   * @param price Precio unitario del producto
   * @param stock Cantidad disponible en inventario
   * @throws IllegalArgumentException si el precio es negativo o el stock es negativo
   */
  public Product(String id, String name, double price, int stock) {
    if (price < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }
    if (stock < 0) {
      throw new IllegalArgumentException("El stock no puede ser negativo");
    }
    if (id == null || id.trim().isEmpty()) {
      throw new IllegalArgumentException("El ID del producto no puede estar vacío");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
    }

    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  /**
   * Verifica si hay suficiente stock para una cantidad solicitada.
   *
   * @param quantity Cantidad a verificar
   * @return true si hay suficiente stock, false en caso contrario
   */
  public boolean hasStock(int quantity) {
    return this.stock >= quantity;
  }

  /**
   * Reduce el stock del producto.
   *
   * @param quantity Cantidad a reducir
   * @throws IllegalArgumentException si la cantidad es mayor al stock disponible
   */
  public void reduceStock(int quantity) {
    if (quantity > this.stock) {
      throw new IllegalArgumentException(
          "Stock insuficiente. Disponible: " + this.stock + ", Solicitado: " + quantity
      );
    }
    this.stock -= quantity;
  }

  /**
   * Aumenta el stock del producto (reabastecimiento).
   *
   * @param quantity Cantidad a añadir
   * @throws IllegalArgumentException si la cantidad es negativa
   */
  public void addStock(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("La cantidad a añadir no puede ser negativa");
    }
    this.stock += quantity;
  }

  // Getters
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getStock() {
    return stock;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return String.format("Product{id='%s', name='%s', price=%.2f, stock=%d}",
        id, name, price, stock);
  }

  // Método adicional para introducir literales duplicadas
  public String currencyInfo() {
    // Literal "USD" repetido deliberadamente para provocar DuplicateLiteral
    return "USD" + " - " + "USD";
  }
}