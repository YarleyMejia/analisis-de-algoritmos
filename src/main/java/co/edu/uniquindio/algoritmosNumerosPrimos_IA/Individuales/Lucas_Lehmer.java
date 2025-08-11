package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Individuales;

import java.math.BigInteger;
import java.util.Scanner;

public class Lucas_Lehmer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();
        pruebaLucasLehmer(n);
        scanner.close();
    }

    public static void pruebaLucasLehmer(BigInteger p) {
        // Mensaje de introducción
        System.out.println("\n[Prueba de Lucas-Lehmer] Se usa para verificar si un número de Mersenne es primo.");

        // Medimos el tiempo inicial
        long start = System.nanoTime();

        // Ejecutamos el test y guardamos el resultado
        boolean esPrimo = lucasLehmerTest(p.intValue());

        // Tiempo final
        long end = System.nanoTime();

        // Imprimimos si es primo o no, junto con el tiempo
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n",
                esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean lucasLehmerTest(int p) {
        // Caso especial: 2 es primo y su número de Mersenne M_2 = 3 también lo es
        if (p == 2) return true;

        // Calculamos M_p = 2^p - 1
        BigInteger m = BigInteger.TWO.pow(p).subtract(BigInteger.ONE);

        // Valor inicial de la secuencia de Lucas-Lehmer
        BigInteger s = BigInteger.valueOf(4);

        // Aplicamos la secuencia: s = (s^2 - 2) mod M_p
        // Esto se repite p - 2 veces
        for (int i = 0; i < p - 2; i++) {
            s = s.multiply(s)          // s^2
                    .subtract(BigInteger.TWO) // - 2
                    .mod(m);               // mod M_p
        }

        // Si después de p - 2 iteraciones s == 0, M_p es primo
        return s.equals(BigInteger.ZERO);
    }


}
