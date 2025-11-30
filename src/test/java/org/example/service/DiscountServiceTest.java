package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de DiscountService")
class DiscountServiceTest {

    private DiscountService service;

    @BeforeEach
    void setUp() {
        service = new DiscountService();
    }

    @Test
    @DisplayName("Aplicar descuento porcentual")
    void testApplyPercentageDiscount() {
        double result = service.applyPercentageDiscount(100.0, 10);
        assertEquals(90.0, result, 0.01);

        result = service.applyPercentageDiscount(200.0, 25);
        assertEquals(150.0, result, 0.01);
    }

    @Test
    @DisplayName("Aplicar descuento fijo")
    void testApplyFixedDiscount() {
        double result = service.applyFixedDiscount(100.0, 10.0);
        assertEquals(90.0, result, 0.01);

        // El resultado no puede ser negativo
        result = service.applyFixedDiscount(50.0, 100.0);
        assertEquals(0.0, result, 0.01);
    }

    @Test
    @DisplayName("Descuento por volumen - sin descuento")
    void testVolumeDiscountNoDiscount() {
        double result = service.applyVolumeDiscount(50.0);
        assertEquals(50.0, result, 0.01);
    }

    @Test
    @DisplayName("Descuento por volumen - 5%")
    void testVolumeDiscount5Percent() {
        double result = service.applyVolumeDiscount(150.0);
        assertEquals(142.5, result, 0.01); // 5% descuento
    }

    @Test
    @DisplayName("Descuento por volumen - 10%")
    void testVolumeDiscount10Percent() {
        double result = service.applyVolumeDiscount(600.0);
        assertEquals(540.0, result, 0.01); // 10% descuento
    }

    @Test
    @DisplayName("Aplicar cupón válido SAVE10")
    void testApplyCouponSave10() {
        double result = service.applyCoupon(100.0, "SAVE10");
        assertEquals(90.0, result, 0.01);
    }

    @Test
    @DisplayName("Lanzar excepción con cupón inválido")
    void testApplyInvalidCoupon() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.applyCoupon(100.0, "INVALID");
        });
    }

    // DELIBERADAMENTE falta testear:
    // - Porcentaje negativo
    // - Porcentaje mayor a 100
    // - Total negativo
    // - Monto de descuento negativo
    // - Descuento por volumen >= 1000 (15%)
    // - Cupones SAVE20 y FLAT50
    // - Cupón con código vacío o nulo
    // - Case insensitive de cupones
}