package org.example.ui;

import java.util.Scanner;

/**
 * Responsable de leer y validar entrada del usuario desde la consola.
 * Maneja errores de formato y reintentos.
 */
public class InputReader {
    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Lee un número entero con validación.
     */
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido.");
            }
        }
    }

    /**
     * Lee un número decimal con validación.
     */
    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido.");
            }
        }
    }

    /**
     * Lee una cadena de texto.
     */
    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Lee una confirmación (S/N).
     */
    public boolean readConfirmation(String prompt) {
        String response = readString(prompt + " (S/N): ");
        return response.equalsIgnoreCase("S");
    }
}