package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Lehmann {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaLehmann(n);
        scanner.close();
    }

    public static void pruebaLehmann(BigInteger n) {
        // Mensaje inicial explicando el tipo de prueba
        System.out.println("\n[Prueba de Lehmann] Algoritmo probabilístico basado en exponenciación modular.");

        // Tomamos el tiempo de inicio
        long start = System.nanoTime();

        // Llamamos al test con 5 iteraciones
        boolean esPrimo = lehmann(n, 5);

        // Tiempo final
        long end = System.nanoTime();

        // Mostramos el resultado y el tiempo de ejecución
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n",
                esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean lehmann(BigInteger n, int k) {
        // Si n < 2, no es primo
        if (n.compareTo(BigInteger.TWO) < 0) return false;

        // Generador de números aleatorios
        Random rand = new Random();

        // Repetimos el test k veces para aumentar la precisión
        for (int i = 0; i < k; i++) {
            // Elegimos a al azar en el rango [1, n - 1]
            BigInteger a = uniformRandom(BigInteger.ONE, n.subtract(BigInteger.ONE), rand);

            // Calculamos r = a^((n-1)/2) mod n
            BigInteger r = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);

            // Si r no es 1 ni n - 1, entonces n no es primo
            if (!r.equals(BigInteger.ONE) && !r.equals(n.subtract(BigInteger.ONE))) return false;
        }

        // Si pasa todas las iteraciones, probablemente es primo
        return true;
    }

    public static BigInteger uniformRandom(BigInteger bottom, BigInteger top, Random rand) {
        BigInteger res; // Variable para almacenar el número aleatorio generado

        do {
            // Genera un número aleatorio con el mismo número de bits que 'top'
            res = new BigInteger(top.bitLength(), rand);
        }
        // Repite mientras el número esté fuera del rango [bottom, top]
        while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);

        // Retorna el número aleatorio dentro del rango
        return res;
    }


}
