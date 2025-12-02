package org.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de Product")
class ProductTest {

    @Test
    @DisplayName("Crear producto válido")
    void testCreateValidProduct() {
        Product product = new Product("P001", "Laptop", 1000.0, 10);

        assertEquals("P001", product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(1000.0, product.getPrice());
        assertEquals(10, product.getStock());

        System.out.println("DEBUG: producto creado");
    }

    @Test
    @DisplayName("Verificar stock disponible")
    void testHasStock() {
        Product product = new Product("P001", "Laptop", 1000.0, 10);

        assertTrue(product.hasStock(5));
        assertTrue(product.hasStock(10));
        assertFalse(product.hasStock(11));
    }

    @Test
    @DisplayName("Reducir stock correctamente")
    void testReduceStock() {
        Product product = new Product("P001", "Laptop", 1000.0, 10);

        product.reduceStock(3);
        assertEquals(7, product.getStock());

        product.reduceStock(7);
        assertEquals(0, product.getStock());
    }

    @Test
    @DisplayName("Agregar stock correctamente")
    void testAddStock() {
        Product product = new Product("P001", "Laptop", 1000.0, 10);

        product.addStock(5);
        assertEquals(15, product.getStock());
    }

    @Test
    @DisplayName("Lanzar excepción al crear producto con precio negativo")
    void testNegativePrice() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Product("P001", "Laptop", -100.0, 10);
        });

        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }

    @Test
    @DisplayName("Test sin aserciones intencional")
    void testWithoutAssertions() {
        // Test vacío sin aserciones para provocar regla TestWithoutAssertions o UnusedTest
        Product p = new Product("P002", "Mouse", 20.0, 5);
        // No hay aserciones aquí intencionalmente
    }

    // DELIBERADAMENTE falta testear:
    // - Stock negativo al crear
    // - ID vacío
    // - Nombre vacío
    // - Reducir más stock del disponible
    // - Agregar stock negativo
    // - equals() y hashCode()
}