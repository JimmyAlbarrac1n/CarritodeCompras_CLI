package org.example.model;

/**
 * Representa un item en el carrito de compras.
 * Contiene un producto y la cantidad seleccionada.
 */
public class CartItem {
    private final Product product;
    private int quantity;

    /**
     * Constructor de CartItem.
     *
     * @param product  Producto del item
     * @param quantity Cantidad del producto
     * @throws IllegalArgumentException si la cantidad es menor o igual a cero
     */
    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Calcula el subtotal del item (precio × cantidad).
     *
     * @return Subtotal del item
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    /**
     * Aumenta la cantidad del item.
     *
     * @param amount Cantidad a añadir
     * @throws IllegalArgumentException si la cantidad es negativa o cero
     */
    public void increaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a aumentar debe ser mayor a cero");
        }
        this.quantity += amount;
    }

    /**
     * Reduce la cantidad del item.
     *
     * @param amount Cantidad a reducir
     * @throws IllegalArgumentException si la reducción resulta en cantidad negativa
     */
    public void decreaseQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a reducir debe ser mayor a cero");
        }
        if (this.quantity - amount < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }
        this.quantity -= amount;
    }

    // Getters
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("CartItem{product=%s, quantity=%d, subtotal=%.2f}",
                product.getName(), quantity, getSubtotal());
    }
}