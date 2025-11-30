package org.example.service;

import org.example.exception.InsufficientStockException;
import org.example.model.CartItem;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gestiona el carrito de compras.
 * Permite agregar, remover y actualizar productos.
 */
public class ShoppingCart {
    private final List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un producto al carrito.
     * Si el producto ya existe, aumenta su cantidad.
     *
     * @param product  Producto a agregar
     * @param quantity Cantidad del producto
     * @throws InsufficientStockException si no hay suficiente stock
     */
    public void addProduct(Product product, int quantity) throws InsufficientStockException {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        // Verificar stock disponible
        if (!product.hasStock(quantity)) {
            throw new InsufficientStockException(
                    product.getId(),
                    quantity,
                    product.getStock()
            );
        }

        // Buscar si el producto ya está en el carrito
        Optional<CartItem> existingItem = findItemByProduct(product);

        if (existingItem.isPresent()) {
            // Si existe, aumentar cantidad
            CartItem item = existingItem.get();
            int newQuantity = item.getQuantity() + quantity;

            // Verificar stock para la nueva cantidad total
            if (!product.hasStock(newQuantity)) {
                throw new InsufficientStockException(
                        product.getId(),
                        newQuantity,
                        product.getStock()
                );
            }

            item.increaseQuantity(quantity);
        } else {
            // Si no existe, crear nuevo item
            items.add(new CartItem(product, quantity));
        }
    }

    /**
     * Remueve un producto del carrito completamente.
     *
     * @param productId ID del producto a remover
     * @return true si se removió, false si no se encontró
     */
    public boolean removeProduct(String productId) {
        return items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**
     * Actualiza la cantidad de un producto en el carrito.
     *
     * @param productId   ID del producto
     * @param newQuantity Nueva cantidad
     * @throws InsufficientStockException si la nueva cantidad excede el stock
     */
    public void updateQuantity(String productId, int newQuantity)
            throws InsufficientStockException {

        Optional<CartItem> item = findItemById(productId);

        if (item.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado en el carrito");
        }

        CartItem cartItem = item.get();
        Product product = cartItem.getProduct();

        if (newQuantity <= 0) {
            removeProduct(productId);
            return;
        }

        // Verificar stock
        if (!product.hasStock(newQuantity)) {
            throw new InsufficientStockException(
                    productId,
                    newQuantity,
                    product.getStock()
            );
        }

        cartItem.setQuantity(newQuantity);
    }

    /**
     * Calcula el total del carrito sin descuentos.
     *
     * @return Total del carrito
     */
    public double getTotal() {
        return items.stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    /**
     * Obtiene el número total de items (cantidad de productos únicos).
     *
     * @return Número de items en el carrito
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Obtiene el número total de productos (suma de cantidades).
     *
     * @return Número total de productos
     */
    public int getTotalProducts() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    /**
     * Vacía el carrito completamente.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Verifica si el carrito está vacío.
     *
     * @return true si está vacío, false en caso contrario
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Obtiene una copia de la lista de items.
     *
     * @return Lista de items del carrito
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    // Métodos privados de utilidad

    private Optional<CartItem> findItemByProduct(Product product) {
        return items.stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();
    }

    private Optional<CartItem> findItemById(String productId) {
        return items.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();
    }
}