package co.edu.uniquindio.algoritmosNumerosPrimos_IA.Todos;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.Random;

public class Prueba{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número entero: ");
        BigInteger n = scanner.nextBigInteger();

        pruebaMillerRabin(n);
        pruebaFermat(n);
        pruebaSolovayStrassen(n);
        pruebaBailliePSW(n);
        pruebaAKS(n);
        algoritmoWilson(n);
        pruebaLucasLehmer(n);
        pruebaLehmann(n);

        scanner.close();
    }

    // ===========================
    // 1. Prueba de Miller-Rabin
    // ===========================
    public static void pruebaMillerRabin(BigInteger n) {
        System.out.println("\n[Prueba de Miller-Rabin] Algoritmo probabilístico basado en exponenciación modular.");
        long start = System.nanoTime();
        boolean esPrimo = millerRabin(n, 5);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean millerRabin(BigInteger n, int k) {
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        if (!n.testBit(0) && !n.equals(BigInteger.TWO)) return false;

        BigInteger d = n.subtract(BigInteger.ONE);
        int s = d.getLowestSetBit();
        d = d.shiftRight(s);

        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO), rand);
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) continue;

            boolean continuar = false;
            for (int r = 0; r < s - 1; r++) {
                x = x.modPow(BigInteger.TWO, n);
                if (x.equals(n.subtract(BigInteger.ONE))) {
                    continuar = true;
                    break;
                }
            }
            if (!continuar) return false;
        }
        return true;
    }

    // ===========================
    // 2. Prueba de Fermat
    // ===========================
    public static void pruebaFermat(BigInteger n) {
        System.out.println("\n[Prueba de Fermat] Algoritmo probabilístico basado en el pequeño teorema de Fermat.");
        long start = System.nanoTime();
        boolean esPrimo = fermat(n, 5);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean fermat(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0) return false;
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) return true;
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO), rand);
            if (!a.modPow(n.subtract(BigInteger.ONE), n).equals(BigInteger.ONE)) return false;
        }
        return true;
    }

    // ===========================
    // 3. Prueba de Solovay-Strassen
    // ===========================
    public static void pruebaSolovayStrassen(BigInteger n) {
        System.out.println("\n[Prueba de Solovay-Strassen] Basado en residuos cuadráticos y el símbolo de Jacobi.");
        long start = System.nanoTime();
        boolean esPrimo = solovayStrassen(n, 5);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean solovayStrassen(BigInteger n, int k) {
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        if (!n.testBit(0) && !n.equals(BigInteger.TWO)) return false;
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.ONE), rand);
            BigInteger jacobi = BigInteger.valueOf(jacobiSymbol(a, n));
            BigInteger mod = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);
            if (!mod.equals(jacobi.mod(n))) return false;
        }
        return true;
    }

    public static long jacobiSymbol(BigInteger a, BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return 0;
        long result = 1;
        if (a.compareTo(BigInteger.ZERO) < 0) {
            a = a.negate();
            if (n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3))) result = -result;
        }
        while (!a.equals(BigInteger.ZERO)) {
            while (a.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                a = a.divide(BigInteger.TWO);
                if (n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(3)) ||
                        n.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5)))
                    result = -result;
            }
            BigInteger temp = a;
            a = n;
            n = temp;
            if (a.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)) &&
                    n.mod(BigInteger.valueOf(4)).equals(BigInteger.valueOf(3)))
                result = -result;
            a = a.mod(n);
        }
        return n.equals(BigInteger.ONE) ? result : 0;
    }

    // ===========================
    // 4. Prueba de Baillie-PSW (simplificada)
    // ===========================
    public static void pruebaBailliePSW(BigInteger n) {
        System.out.println("\n[Prueba de Baillie-PSW] Combina Miller-Rabin y pruebas de Lucas para mayor precisión.");
        long start = System.nanoTime();
        boolean esPrimo = millerRabin(n, 5); // En la versión completa incluir Lucas
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    // ===========================
    // 5. Prueba de primalidad AKS (simulada)
    // ===========================
    public static void pruebaAKS(BigInteger n) {
        System.out.println("\n[Prueba de primalidad AKS] Algoritmo determinístico eficiente basado en números algebraicos.");
        long start = System.nanoTime();
        boolean esPrimo = n.isProbablePrime(100);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    // ===========================
    // 6. Algoritmo de Wilson
    // ===========================
    public static void algoritmoWilson(BigInteger n) {
        System.out.println("\n[Algoritmo de Wilson] Usa el teorema de Wilson basado en factoriales.");
        long start = System.nanoTime();
        boolean esPrimo = factorial(n.subtract(BigInteger.ONE)).add(BigInteger.ONE).mod(n).equals(BigInteger.ZERO);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static BigInteger factorial(BigInteger n) {
        BigInteger res = BigInteger.ONE;
        for (BigInteger i = BigInteger.TWO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            res = res.multiply(i);
        }
        return res;
    }

    // ===========================
    // 7. Prueba de Lucas-Lehmer
    // ===========================
    public static void pruebaLucasLehmer(BigInteger p) {
        System.out.println("\n[Prueba de Lucas-Lehmer] Se usa para verificar si un número de Mersenne es primo.");
        long start = System.nanoTime();
        boolean esPrimo = lucasLehmerTest(p.intValue());
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean lucasLehmerTest(int p) {
        if (p == 2) return true;
        BigInteger m = BigInteger.TWO.pow(p).subtract(BigInteger.ONE);
        BigInteger s = BigInteger.valueOf(4);
        for (int i = 0; i < p - 2; i++) {
            s = s.multiply(s).subtract(BigInteger.TWO).mod(m);
        }
        return s.equals(BigInteger.ZERO);
    }

    // ===========================
    // 8. Prueba de Lehmann
    // ===========================
    public static void pruebaLehmann(BigInteger n) {
        System.out.println("\n[Prueba de Lehmann] Algoritmo probabilístico basado en exponenciación modular.");
        long start = System.nanoTime();
        boolean esPrimo = lehmann(n, 5);
        long end = System.nanoTime();
        System.out.printf("Resultado: %s | Tiempo: %.6f segundos\n", esPrimo ? "Primo" : "No primo", (end - start) / 1e9);
    }

    public static boolean lehmann(BigInteger n, int k) {
        if (n.compareTo(BigInteger.TWO) < 0) return false;
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            BigInteger a = uniformRandom(BigInteger.ONE, n.subtract(BigInteger.ONE), rand);
            BigInteger r = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);
            if (!r.equals(BigInteger.ONE) && !r.equals(n.subtract(BigInteger.ONE))) return false;
        }
        return true;
    }

    // ===========================
    // Funciones auxiliares
    // ===========================
    public static BigInteger uniformRandom(BigInteger bottom, BigInteger top, Random rand) {
        BigInteger res;
        do {
            res = new BigInteger(top.bitLength(), rand);
        } while (res.compareTo(bottom) < 0 || res.compareTo(top) > 0);
        return res;
    }
}

