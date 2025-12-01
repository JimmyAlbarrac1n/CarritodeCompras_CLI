package org.example.ui;

import org.example.exception.InsufficientStockException;
import org.example.model.Product;
import org.example.service.DiscountService;
import org.example.service.ShoppingCart;
import org.example.service.ShoppingCartService;

import java.util.Scanner;

/**
 * Controlador principal de la interfaz de consola.
 * Coordina todas las vistas y maneja el flujo de la aplicación.
 */
public class ConsoleUI {

  private final ShoppingCartService service;
  private final MenuView menuView;
  private final CatalogView catalogView;
  private final CartView cartView;
  private final InputReader inputReader;
  private final DiscountService discountService;

  public ConsoleUI() {
    this.service = new ShoppingCartService();
    this.menuView = new MenuView();
    this.catalogView = new CatalogView();
    this.cartView = new CartView();
    this.inputReader = new InputReader(new Scanner(System.in));
    this.discountService = new DiscountService();
  }

  /**
   * Inicia la aplicación.
   */
  public void start() {
    menuView.showWelcome();

    boolean running = true;

    while (running) {
      menuView.showMainMenu();
      int option = inputReader.readInt("Seleccione una opción: ");

      switch (option) {
        case 1 -> showCatalog();
        case 2 -> addProductToCart();
        case 3 -> viewCart();
        case 4 -> updateQuantity();
        case 5 -> removeProduct();
        case 6 -> applyDiscount();
        case 7 -> checkout();
        case 8 -> clearCart();
        case 9 -> {
          menuView.showGoodbye();
          running = false;
        }
        default -> menuView.showError("Opción inválida. Intente nuevamente.");
      }
    }
  }

  private void showCatalog() {
    catalogView.displayCatalog(service.getAllProducts());
  }

  private void addProductToCart() {
    System.out.println("\n➕ AGREGAR PRODUCTO AL CARRITO");
    System.out.println("─────────────────────────────────");

    String productId = inputReader.readString("Ingrese el ID del producto: ").toUpperCase();
    Product product = service.getProduct(productId);

    if (product == null) {
      menuView.showError("Producto no encontrado.");
      return;
    }

    catalogView.displayProductDetails(product);
    int quantity = inputReader.readInt("Ingrese la cantidad: ");

    try {
      service.addToCart(productId, quantity);
      menuView.showSuccess("Producto agregado exitosamente.");
    } catch (InsufficientStockException e) {
      menuView.showError(e.getMessage());
    } catch (IllegalArgumentException e) {
      menuView.showError("Error: " + e.getMessage());
    }
  }

  private void viewCart() {
    ShoppingCart cart = service.getCart();
    cartView.displayCart(
        cart.getItems(),
        cart.getItemCount(),
        cart.getTotalProducts(),
        cart.getTotal()
    );
  }

  private void updateQuantity() {
    ShoppingCart cart = service.getCart();
    if (cart.isEmpty()) {
      menuView.showError("El carrito está vacío.");
      return;
    }

    viewCart();

    System.out.println("✏️  ACTUALIZAR CANTIDAD");
    System.out.println("─────────────────────────");

    String productId = inputReader.readString("Ingrese el ID del producto: ").toUpperCase();
    int newQuantity = inputReader.readInt("Ingrese la nueva cantidad (0 para eliminar): ");

    try {
      cart.updateQuantity(productId, newQuantity);
      menuView.showSuccess("Cantidad actualizada exitosamente.");
    } catch (InsufficientStockException e) {
      menuView.showError(e.getMessage());
    } catch (IllegalArgumentException e) {
      menuView.showError("Error: " + e.getMessage());
    }
  }

  private void removeProduct() {
    ShoppingCart cart = service.getCart();
    if (cart.isEmpty()) {
      menuView.showError("El carrito está vacío.");
      return;
    }

    viewCart();

    System.out.println("❌ REMOVER PRODUCTO");
    System.out.println("────────────────────");

    String productId = inputReader.readString("Ingrese el ID del producto a remover: ").toUpperCase();

    if (cart.removeProduct(productId)) {
      menuView.showSuccess("Producto removido exitosamente.");
    } else {
      menuView.showError("Producto no encontrado en el carrito.");
    }
  }

  private void applyDiscount() {
    ShoppingCart cart = service.getCart();
    if (cart.isEmpty()) {
      menuView.showError("El carrito está vacío.");
      return;
    }

    double originalTotal = cart.getTotal();
    menuView.showDiscountMenu(originalTotal);

    int option = inputReader.readInt("Seleccione tipo de descuento: ");

    try {
      double finalTotal = switch (option) {
        case 1 -> {
          double percent = inputReader.readDouble("Ingrese el porcentaje (0-100): ");
          yield discountService.applyPercentageDiscount(originalTotal, percent);
        }
        case 2 -> {
          double amount = inputReader.readDouble("Ingrese el monto de descuento: ");
          yield discountService.applyFixedDiscount(originalTotal, amount);
        }
        case 3 -> discountService.applyVolumeDiscount(originalTotal);
        case 4 -> {
          String coupon = inputReader.readString(
              "Ingrese el código del cupón (SAVE10, SAVE20, FLAT50): "
          );
          yield discountService.applyCoupon(originalTotal, coupon);
        }
        default -> {
          menuView.showError("Opción inválida.");
          yield originalTotal;
        }
      };

      double discount = originalTotal - finalTotal;
      if (discount > 0) {
        cartView.displayDiscount(originalTotal, discount, finalTotal);
      }

    } catch (IllegalArgumentException e) {
      menuView.showError("Error: " + e.getMessage());
    }
  }

  private void checkout() {
    ShoppingCart cart = service.getCart();
    if (cart.isEmpty()) {
      menuView.showError("El carrito está vacío. No hay nada que comprar.");
      return;
    }

    System.out.println("\n💳 FINALIZAR COMPRA");
    System.out.println("════════════════════════════════════════");
    viewCart();

    if (inputReader.readConfirmation("¿Confirmar compra?")) {
      System.out.printf("\n✅ ¡Compra realizada exitosamente!%n");
      System.out.printf("Total pagado: $%.2f%n", cart.getTotal());
      System.out.println("Gracias por su compra. 🎉\n");
      cart.clear();
    } else {
      menuView.showInfo("Compra cancelada.");
    }
  }

  private void clearCart() {
    ShoppingCart cart = service.getCart();
    if (cart.isEmpty()) {
      menuView.showError("El carrito ya está vacío.");
      return;
    }

    if (inputReader.readConfirmation("¿Está seguro de vaciar el carrito?")) {
      cart.clear();
      menuView.showSuccess("Carrito vaciado exitosamente.");
    } else {
      menuView.showInfo("Operación cancelada.");
    }
  }
}