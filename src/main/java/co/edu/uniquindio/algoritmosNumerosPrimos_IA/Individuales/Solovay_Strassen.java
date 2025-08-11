package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Solovay_Strassen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaSolovayStrassen(n);
        scanner.close();
    }

    // Método principal para ejecutar la prueba de Solovay-Strassen
    public static void pruebaSolovayStrassen(BigInteger n) {
        // Descripción del algoritmo
        System.out.println("\n[Prueba de Solovay-Strassen] Basado en residuos cuadráticos y el símbolo de Jacobi.");

        // Medimos el tiempo inicial
        long start = System.nanoTime();

        // Ejecuta la prueba con k = 5 iteraciones
        boolean esPrimo = solovayStrassen(n, 5);

        // Tiempo final
        long end = System.nanoTime();

        // Muestra el resultado y el tiempo en segundos
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n",
                esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean solovayStrassen(BigInteger n, int k) {
        // Si el número es menor que 2, no es primo
        if (n.compareTo(BigInteger.TWO) < 0) return false;

        // Si es par y distinto de 2, no es primo
        if (!n.testBit(0) && !n.equals(BigInteger.TWO)) return false;

        // Generador de números aleatorios
        Random rand = new Random();

        // Repetimos la prueba k veces para aumentar la precisión
        for (int i = 0; i < k; i++) {
            // Elegimos un número aleatorio 'a' en el rango [2, n-1]
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.ONE), rand);

            // Calculamos el símbolo de Jacobi (a/n)
            BigInteger jacobi = BigInteger.valueOf(jacobiSymbol(a, n));

            // Calculamos a^((n-1)/2) mod n
            BigInteger mod = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);

            // Si no se cumple la condición de la prueba, el número es compuesto
            if (!mod.equals(jacobi.mod(n))) return false;
        }

        // Si pasa todas las pruebas, probablemente es primo
        return true;
    }

    //El símbolo de Jacobi (a/n) es una generalización del símbolo de Legendre y
    // se usa para verificar propiedades de residuos cuadráticos.
    public static long jacobiSymbol(BigInteger a, BigInteger n) {
        // Si n es par, el símbolo de Jacobi no está definido (retorna 0)
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return 0;

        long result = 1;

        // Si a es negativo, ajustamos su valor y modificamos el signo del resultado según n mod 4
        if (a.compareTo(BigInteger.ZERO) < 0) {
            a = a.negate();
            if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)))
                result = -result;
        }

        // Bucle mientras a no sea cero
        while (!a.equals(BigInteger.ZERO)) {

            // Reducimos factores de 2 en 'a'
            while (a.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                a = a.divide(BigInteger.TWO);
                // Ajuste de signo según n mod 8
                if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) ||
                        n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5)))
                    result = -result;
            }

            // Intercambiamos 'a' y 'n' (propiedad de reciprocidad cuadrática)
            BigInteger temp = a;
            a = n;
            n = temp;

            // Ajuste de signo si a ≡ 3 (mod 4) y n ≡ 3 (mod 4)
            if (a.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) &&
                    n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)))
                result = -result;

            // Reducimos a módulo n
            a = a.mod(n);
        }

        // Si n == 1, devolvemos el resultado, si no, el símbolo es 0
        return n.equals(BigInteger.ONE) ? result : 0;
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
