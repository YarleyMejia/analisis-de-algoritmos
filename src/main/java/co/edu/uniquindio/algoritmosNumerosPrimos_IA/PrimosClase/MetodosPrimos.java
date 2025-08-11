package co.edu.uniquindio.algoritmosNumerosPrimos_IA.PrimosClase;

import java.util.Scanner;

public class MetodosPrimos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        int numero = sc.nextInt();

        System.out.println("\nComparando métodos de primalidad:\n");

        long inicio, fin;

        // Método 1
        inicio = System.nanoTime();
        boolean r1 = determinarNumeroPrimo1(numero);
        fin = System.nanoTime();
        System.out.printf("Método 1 | Resultado: %-5s | Tiempo: %.9f segundos\n", r1 ? "Primo" : "No", (fin - inicio) / 1e9);

        // Método 2
        inicio = System.nanoTime();
        boolean r2 = determinarNumeroPrimo2(numero);
        fin = System.nanoTime();
        System.out.printf("Método 2 | Resultado: %-5s | Tiempo: %.9f segundos\n", r2 ? "Primo" : "No", (fin - inicio) / 1e9);

        // Método 3
        inicio = System.nanoTime();
        boolean r3 = determinarNumeroPrimo3(numero);
        fin = System.nanoTime();
        System.out.printf("Método 3 | Resultado: %-5s | Tiempo: %.9f segundos\n", r3 ? "Primo" : "No", (fin - inicio) / 1e9);

        // Método 4
        inicio = System.nanoTime();
        boolean r4 = determinarNumeroPrimo4(numero);
        fin = System.nanoTime();
        System.out.printf("Método 4 | Resultado: %-5s | Tiempo: %.9f segundos\n", r4 ? "Primo" : "No", (fin - inicio) / 1e9);

        // Método 5
        inicio = System.nanoTime();
        boolean r5 = determinarNumeroPrimo5(numero);
        fin = System.nanoTime();
        System.out.printf("Método 5 | Resultado: %-5s | Tiempo: %.9f segundos\n", r5 ? "Primo" : "No", (fin - inicio) / 1e9);

        sc.close();
    }

    public static boolean determinarNumeroPrimo1(int numero) {
        int resultado = 0;
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                resultado = 1;
            }
        }
        return resultado == 0;
    }

    public static boolean determinarNumeroPrimo2(int numero) {
        boolean centi = true;
        int i;
        for (i = 2; i <= numero / 2 && centi; i++) {
            if (numero % i == 0) {
                centi = false;
            }
        }
        return centi;
    }

    public static boolean determinarNumeroPrimo3(int numero) {
        int i;
        for (i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) {
                break;
            }
        }
        return numero / 2 < i;
    }

    public static boolean determinarNumeroPrimo4(int numero) {
        for (int i = 2; i <= (int) Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean determinarNumeroPrimo5(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}


