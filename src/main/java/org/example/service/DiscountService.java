package org.example.service;

/**
 * Servicio para aplicar descuentos al carrito de compras.
 */
public class DiscountService {

  /**
   * Aplica un descuento porcentual al total.
   *
   * @param total           Total antes del descuento
   * @param discountPercent Porcentaje de descuento (0-100)
   * @return Total después del descuento
   * @throws IllegalArgumentException si el porcentaje es inválido
   */
  public double applyPercentageDiscount(double total, double discountPercent) {
    if (discountPercent < 0 || discountPercent > 100) {
      throw new IllegalArgumentException(
          "El porcentaje de descuento debe estar entre 0 y 100"
      );
    }
    if (total < 0) {
      throw new IllegalArgumentException("El total no puede ser negativo");
    }

    double discountAmount = total * (discountPercent / 100);
    return total - discountAmount;
  }

  /**
   * Aplica un descuento fijo al total.
   *
   * @param total          Total antes del descuento
   * @param discountAmount Monto fijo de descuento
   * @return Total después del descuento (mínimo 0)
   * @throws IllegalArgumentException si el monto es negativo
   */
  public double applyFixedDiscount(double total, double discountAmount) {
    if (discountAmount < 0) {
      throw new IllegalArgumentException("El monto de descuento no puede ser negativo");
    }
    if (total < 0) {
      throw new IllegalArgumentException("El total no puede ser negativo");
    }

    double result = total - discountAmount;
    return Math.max(result, 0); // No puede ser negativo
  }

  /**
   * Calcula el descuento según el total de compra (descuento por volumen).
   * - Compras mayores a $1000: 15% de descuento
   * - Compras entre $500-$999: 10% de descuento
   * - Compras entre $100-$499: 5% de descuento
   * - Compras menores a $100: Sin descuento
   *
   * @param total Total de la compra
   * @return Total después del descuento
   */
  public double applyVolumeDiscount(double total) {
    if (total < 0) {
      throw new IllegalArgumentException("El total no puede ser negativo");
    }

    double discountPercent = 0;

    if (total >= 1000) {
      discountPercent = 15;
    } else if (total >= 500) {
      discountPercent = 10;
    } else if (total >= 100) {
      discountPercent = 5;
    }

    return applyPercentageDiscount(total, discountPercent);
  }

  /**
   * Aplica un cupón de descuento si es válido.
   *
   * @param total      Total antes del descuento
   * @param couponCode Código del cupón
   * @return Total después del descuento
   */
  public double applyCoupon(double total, String couponCode) {
    if (total < 0) {
      throw new IllegalArgumentException("El total no puede ser negativo");
    }
    if (couponCode == null || couponCode.trim().isEmpty()) {
      throw new IllegalArgumentException("El código de cupón no puede estar vacío");
    }

    // Cupones de ejemplo
    return switch (couponCode.toUpperCase()) {
      case "SAVE10" -> applyPercentageDiscount(total, 10);
      case "SAVE20" -> applyPercentageDiscount(total, 20);
      case "FLAT50" -> applyFixedDiscount(total, 50);
      default -> throw new IllegalArgumentException("Cupón inválido: " + couponCode);
    };
  }
}