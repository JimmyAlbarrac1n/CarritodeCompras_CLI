package org.example.service;

import org.example.exception.InsufficientStockException;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de ShoppingCart")
class ShoppingCartTest {

    private ShoppingCart cart;
    private Product laptop;
    private Product mouse;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
        laptop = new Product("P001", "Laptop", 1000.0, 5);
        mouse = new Product("P002", "Mouse", 25.0, 10);
    }

    @Test
    @DisplayName("Carrito nuevo está vacío")
    void testNewCartIsEmpty() {
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItemCount());
        assertEquals(0.0, cart.getTotal());
    }

    @Test
    @DisplayName("Agregar producto al carrito")
    void testAddProduct() throws InsufficientStockException {
        cart.addProduct(laptop, 2);

        assertFalse(cart.isEmpty());
        assertEquals(1, cart.getItemCount());
        assertEquals(2000.0, cart.getTotal());
    }

    @Test
    @DisplayName("Agregar múltiples productos")
    void testAddMultipleProducts() throws InsufficientStockException {
        cart.addProduct(laptop, 1);
        cart.addProduct(mouse, 2);

        assertEquals(2, cart.getItemCount());
        assertEquals(1050.0, cart.getTotal());
    }

    @Test
    @DisplayName("Agregar mismo producto aumenta cantidad")
    void testAddSameProductIncreasesQuantity() throws InsufficientStockException {
        cart.addProduct(laptop, 1);
        cart.addProduct(laptop, 2);

        assertEquals(1, cart.getItemCount());
        assertEquals(3, cart.getTotalProducts());
        assertEquals(3000.0, cart.getTotal());
    }

    @Test
    @DisplayName("Lanzar excepción por stock insuficiente")
    void testInsufficientStock() {
        InsufficientStockException exception = assertThrows(
                InsufficientStockException.class,
                () -> cart.addProduct(laptop, 10)
        );

        assertEquals("P001", exception.getProductId());
        assertEquals(10, exception.getRequested());
        assertEquals(5, exception.getAvailable());
    }

    @Test
    @DisplayName("Remover producto del carrito")
    void testRemoveProduct() throws InsufficientStockException {
        cart.addProduct(laptop, 1);
        cart.addProduct(mouse, 1);

        assertTrue(cart.removeProduct("P001"));
        assertEquals(1, cart.getItemCount());
        assertFalse(cart.removeProduct("P001")); // Ya no existe
    }

    @Test
    @DisplayName("Limpiar carrito")
    void testClearCart() throws InsufficientStockException {
        cart.addProduct(laptop, 1);
        cart.addProduct(mouse, 1);

        cart.clear();
        assertTrue(cart.isEmpty());
    }

    // DELIBERADAMENTE falta testear:
    // - updateQuantity() con diferentes escenarios
    // - Agregar producto nulo
    // - Agregar cantidad negativa o cero
    // - getTotalProducts()
    // - getItems()
    // - updateQuantity() para remover (cantidad 0)
    // - Stock insuficiente al actualizar cantidad
}