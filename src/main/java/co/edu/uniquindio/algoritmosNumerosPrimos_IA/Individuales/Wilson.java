package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Scanner;

public class Wilson {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaWilson(n);
        scanner.close();
    }
    public static void pruebaWilson(BigInteger n) {
        // Mensaje descriptivo del método y teoría de base
        System.out.println("\n[Algoritmo de Wilson] Usa el teorema de Wilson basado en factoriales.");
        // Teorema de Wilson: Un número n > 1 es primo ⇔ (n-1)! ≡ -1 (mod n)

        // Guardar el tiempo de inicio (en nanosegundos)
        long start = System.nanoTime();

        // Calcular factorial(n - 1), sumarle 1 y verificar si el resultado mod n == 0
        // Esto equivale a comprobar: (n-1)! + 1 divisible entre n
        boolean esPrimo = factorial(n.subtract(BigInteger.ONE))
                .add(BigInteger.ONE)
                .mod(n)
                .equals(BigInteger.ZERO);

        // Guardar el tiempo de finalización
        long end = System.nanoTime();

        // Mostrar el resultado y el tiempo transcurrido en segundos
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n",
                esPrimo ? "Primo" : "No primo",
                (end - start) / 1e9);
    }

    public static BigInteger factorial(BigInteger n) {
        // Inicializamos el resultado en 1 (por definición 0! = 1 y 1! = 1)
        BigInteger res = BigInteger.ONE;

        // Bucle desde 2 hasta n (inclusive)
        // i se incrementa de 1 en 1 usando i.add(BigInteger.ONE)
        for (BigInteger i = BigInteger.TWO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            // Multiplicamos el acumulador 'res' por el valor actual de i
            res = res.multiply(i);
        }

        // Al final, 'res' contiene n! (n factorial)
        return res;
    }


}
