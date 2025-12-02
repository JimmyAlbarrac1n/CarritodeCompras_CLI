package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de CartItem")
class CartItemTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("P001", "Laptop", 100.0, 10);
    }

    @Test
    @DisplayName("Crear CartItem válido")
    void testCreateCartItem() {
        CartItem item = new CartItem(product, 2);

        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());

        System.out.println("DEBUG: CartItem creado");
    }

    @Test
    @DisplayName("Calcular subtotal correctamente")
    void testGetSubtotal() {
        CartItem item = new CartItem(product, 3);

        assertEquals(300.0, item.getSubtotal());
    }

    @Test
    @DisplayName("Aumentar cantidad")
    void testIncreaseQuantity() {
        CartItem item = new CartItem(product, 2);

        item.increaseQuantity(3);
        assertEquals(5, item.getQuantity());
    }

    @Test
    @DisplayName("Lanzar excepción con cantidad cero")
    void testZeroQuantity() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CartItem(product, 0);
        });
    }

    @Test
    @DisplayName("Test que imprime stack trace")
    void testPrintStackTrace() {
        try {
            throw new RuntimeException("Forzar excepción");
        } catch (RuntimeException e) {
            e.printStackTrace(); // Intencional para provocar AvoidPrintStackTrace
        }
    }

    // DELIBERADAMENTE falta testear:
    // - Producto nulo
    // - Cantidad negativa
    // - decreaseQuantity()
    // - setQuantity()
    // - Casos edge de increase/decrease
}