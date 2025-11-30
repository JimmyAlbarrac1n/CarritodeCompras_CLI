package org.example.ui;

import org.example.model.Product;
import java.util.List;

/**
 * Responsable de mostrar el catálogo de productos.
 */
public class CatalogView {

    /**
     * Muestra la lista de productos disponibles.
     */
    public void displayCatalog(List<Product> products) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    CATÁLOGO DE PRODUCTOS                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-6s │ %-30s │ %-10s │ %-6s ║%n", "ID", "Nombre", "Precio", "Stock");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");

        for (Product product : products) {
            System.out.printf("║ %-6s │ %-30s │ $%-9.2f │ %-6d ║%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
            );
        }

        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Muestra detalles de un producto específico.
     */
    public void displayProductDetails(Product product) {
        System.out.println("Producto seleccionado: " + product.getName());
        System.out.println("Precio: $" + product.getPrice());
        System.out.println("Stock disponible: " + product.getStock());
    }
}