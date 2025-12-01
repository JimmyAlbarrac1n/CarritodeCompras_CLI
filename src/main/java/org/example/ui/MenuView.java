package org.example.ui;

/**
 * Responsable de mostrar los diferentes menús de la aplicación.
 */
public class MenuView {

  /**
   * Muestra el mensaje de bienvenida.
   */
  public void showWelcome() {
    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║   🛒 SISTEMA DE CARRITO DE COMPRAS   ║");
    System.out.println("║        ¡Bienvenido a TechStore!       ║");
    System.out.println("╚════════════════════════════════════════╝\n");
  }

  /**
   * Muestra el menú principal.
   */
  public void showMainMenu() {
    System.out.println("┌─────────────────────────────────────┐");
    System.out.println("│           MENÚ PRINCIPAL            │");
    System.out.println("├─────────────────────────────────────┤");
    System.out.println("│ 1. 📋 Ver catálogo de productos     │");
    System.out.println("│ 2. ➕ Agregar producto al carrito   │");
    System.out.println("│ 3. 🛒 Ver carrito                   │");
    System.out.println("│ 4. ✏️  Actualizar cantidad           │");
    System.out.println("│ 5. ❌ Remover producto               │");
    System.out.println("│ 6. 🎫 Aplicar descuento             │");
    System.out.println("│ 7. 💳 Finalizar compra              │");
    System.out.println("│ 8. 🗑️  Vaciar carrito                │");
    System.out.println("│ 9. 🚪 Salir                         │");
    System.out.println("└─────────────────────────────────────┘");
  }

  /**
   * Muestra el menú de descuentos.
   */
  public void showDiscountMenu(double currentTotal) {
    System.out.println("\n🎫 APLICAR DESCUENTO");
    System.out.println("════════════════════════════════════");
    System.out.printf("Total actual: $%.2f%n", currentTotal);
    System.out.println("────────────────────────────────────");
    System.out.println("1. Descuento por porcentaje");
    System.out.println("2. Descuento fijo");
    System.out.println("3. Descuento por volumen (automático)");
    System.out.println("4. Aplicar cupón");
    System.out.println("════════════════════════════════════");
  }

  /**
   * Muestra un mensaje de éxito.
   */
  public void showSuccess(String message) {
    System.out.println("✅ " + message + "\n");
  }

  /**
   * Muestra un mensaje de error.
   */
  public void showError(String message) {
    System.out.println("❌ " + message + "\n");
  }

  /**
   * Muestra un mensaje informativo.
   */
  public void showInfo(String message) {
    System.out.println("ℹ️  " + message + "\n");
  }

  /**
   * Muestra mensaje de despedida.
   */
  public void showGoodbye() {
    System.out.println("\n¡Gracias por usar nuestro sistema! 👋");
  }
}