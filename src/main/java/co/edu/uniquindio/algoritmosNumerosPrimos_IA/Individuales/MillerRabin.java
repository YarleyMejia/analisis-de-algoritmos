package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import static co.edu.uniquindio.algoritmosNumerosPrimos_IA.Todos.Prueba.uniformRandom;

public class MillerRabin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaMillerRabin(n);
        scanner.close();
    }

    public static void pruebaMillerRabin(BigInteger n) {
        System.out.println("\n[Prueba de Miller-Rabin] Algoritmo probabilístico basado en exponenciación modular.");
        long start = System.nanoTime();
        boolean esPrimo = millerRabin(n, 5);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean millerRabin(BigInteger n, int k) {
        // Si el número es menor que 2, no es primo
        if (n.compareTo(BigInteger.TWO) < 0) return false;

        // Si el número es par y no es 2, no es primo
        if (!n.testBit(0) && !n.equals(BigInteger.TWO)) return false;

        // Calcula d = n - 1
        BigInteger d = n.subtract(BigInteger.ONE);

        // Obtiene el número de ceros finales en la representación binaria de d (factor de 2^s)
        int s = d.getLowestSetBit();

        // Divide d entre 2^s para que d sea impar
        d = d.shiftRight(s);

        // Generador de números aleatorios para escoger bases de prueba
        Random rand = new Random();

        // Repetir la prueba k veces para aumentar la precisión
        for (int i = 0; i < k; i++) {
            // Escoge un número aleatorio 'a' tal que 2 <= a <= n-2
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO), rand);

            // Calcula x = a^d mod n usando exponenciación modular rápida
            BigInteger x = a.modPow(d, n);

            // Si x es 1 o n-1, esta iteración pasa la prueba
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;

            // Bandera para saber si se encontró n-1 en las potencias sucesivas
            boolean continuar = false;

            // Eleva x al cuadrado s-1 veces (mod n) para ver si se obtiene n-1
            for (int r = 0; r < s - 1; r++) {
                x = x.modPow(BigInteger.TWO, n); // x = x^2 mod n

                // Si en algún momento x = n-1, pasa la prueba con esta base
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    continuar = true;
                    break; // Salir del bucle interno
                }
            }

            // Si no se encontró n-1 en ninguna potencia, el número es compuesto
            if (!continuar) return false;
        }

        // Si pasó todas las iteraciones, es probablemente primo
        return true;
    }
}

