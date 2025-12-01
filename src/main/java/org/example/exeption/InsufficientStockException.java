package org.example.exception;

/**
 * Excepción lanzada cuando se intenta agregar más productos de los disponibles en stock.
 */
public class InsufficientStockException extends Exception {

  private final String productId;
  private final int requested;
  private final int available;

  /**
   * Constructor de InsufficientStockException.
   *
   * @param productId Identificador del producto
   * @param requested Cantidad solicitada
   * @param available Cantidad disponible
   */
  public InsufficientStockException(String productId, int requested, int available) {
    super(String.format(
        "Stock insuficiente para producto '%s'. Solicitado: %d, Disponible: %d",
        productId, requested, available
    ));
    this.productId = productId;
    this.requested = requested;
    this.available = available;
  }

  public String getProductId() {
    return productId;
  }

  public int getRequested() {
    return requested;
  }

  public int getAvailable() {
    return available;
  }
}