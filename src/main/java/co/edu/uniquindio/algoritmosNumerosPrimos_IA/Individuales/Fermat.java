package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Fermat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaFermat(n);
        scanner.close();
    }

    public static void pruebaFermat(BigInteger n) {
        // Mensaje inicial explicando de qué algoritmo se trata
        System.out.println("\n[Prueba de Fermat] Algoritmo probabilístico basado en el pequeño teorema de Fermat.");

        // Guardar el tiempo inicial en nanosegundos
        long start = System.nanoTime();

        // Llamar a la función que implementa la prueba de Fermat, con k=5 iteraciones
        boolean esPrimo = fermat(n, 5);

        // Guardar el tiempo final en nanosegundos
        long end = System.nanoTime();

        // Mostrar el resultado (Primo o No primo) y el tiempo de ejecución en segundos
        System.out.printf(
                "Resultado: %s | Tiempo: %.6f segundos\n",
                esPrimo ? "Primo" : "No primo",
                (end - start) / 1e9
        );
    }

    public static boolean fermat(BigInteger n, int k) {
        // Si n <= 1, no es primo
        if (n.compareTo(BigInteger.ONE) <= 0) return false;

        // Si n es 2 o 3, es primo
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) return true;

        // Generador de números aleatorios
        Random rand = new Random();

        // Repetir la prueba k veces para aumentar la probabilidad de acierto
        for (int i = 0; i < k; i++) {
            // Elegir un entero aleatorio 'a' tal que 2 <= a <= n-2
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO), rand);

            // Si a^(n-1) mod n != 1, entonces n es compuesto según el teorema de Fermat
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE))
                return false;
        }

        // Si pasa todas las pruebas, n es probablemente primo
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
