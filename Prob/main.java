
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * B - moment pierwszej kolizji
 * U - liczba pustych urn po wrzuceniu n kul
 * L - maksymalna liczba kul w urnie po wrzuceniu n kul
 * C - minimalna liczba rzutów, po której w kazdej z urn jest co najmniej jedna
 * kula
 * D - minimalna liczba rzutów, po której w kazdej z urn sa co najmniej dwie
 * kule
 */
public class main {
    private static final Exception IOException = null;

    static int maxArray(int[] arr) {
        int max = arr[0];
        for (int x : arr) {
            if (x > max)
                max = x;
        }
        return max;
    }

    static int lessThan(int[] arr, int less) {
        int empty = 0;
        for (int x : arr) {
            if (x < less)
                empty++;
        }
        return empty;
    }

    public static void simulate(int n, Random gen) throws java.io.IOException {
        double i, B, U, L, C, D, fB, fU, fL, fC, fD;
        int[] urny = new int[n];
        fB = fU = fL = fC = fD = 0;
        int testCount = 50;

        BufferedWriter writerAvarages = new BufferedWriter(new FileWriter("Avarages.txt", true));

        BufferedWriter writerB = new BufferedWriter(new FileWriter("B.txt", true));
        BufferedWriter writerU = new BufferedWriter(new FileWriter("U.txt", true));
        BufferedWriter writerL = new BufferedWriter(new FileWriter("L.txt", true));
        BufferedWriter writerC = new BufferedWriter(new FileWriter("C.txt", true));
        BufferedWriter writerD = new BufferedWriter(new FileWriter("D.txt", true));
        BufferedWriter writeri = new BufferedWriter(new FileWriter("i.txt", true));
        writerB.append(n + ";");
        writerU.append(n + ";");
        writerL.append(n + ";");
        writerC.append(n + ";");
        writerD.append(n + ";");
        writeri.append(n + ";");

        for (int k = 0; k < testCount; k++) {
            B = 0;
            U = 0;
            L = 0;
            C = 0;
            D = 0;
            i = 0;
            Arrays.fill(urny, 0);
            while (D == 0) {
                i++;
                int urn = (int) (gen.nextDouble() * n);
                if (B == 0 && urny[urn] == 1)
                    B = i;
                urny[urn]++;
                if (i == n) {
                    U = lessThan(urny, 1);
                    L = maxArray(urny);
                }
                if (C == 0 && lessThan(urny, 1) == 0)
                    C = i;
                if (D == 0 && lessThan(urny, 2) == 0)
                    D = i;
            }
            fB += B;
            fU += U;
            fL += L;
            fC += C;
            fD += D;

            writerB.append(B + ";");
            writerU.append(U + ";");
            writerL.append(L + ";");
            writerD.append(D + ";");
            writerC.append(C + ";");
            writeri.append(i + ";");

        }
        writerB.append("\n");
        writerU.append("\n");
        writerL.append("\n");
        writerD.append("\n");
        writerC.append("\n");
        writeri.append("\n");

        writerB.close();
        writerU.close();
        writerL.close();
        writerD.close();
        writerC.close();
        writeri.close();
        fB = fB / testCount;
        fU = fU / testCount;
        fL = fL / testCount;
        fC = fC / testCount;
        fD = fD / testCount;

        writerAvarages.append(n + ";" + fB + ";" + fU + ";" + fL + ";" + fC + ";" + fD + ";" + (fD - fC) + "\n");
        writerAvarages.close();
    }

    public static void main(String[] args) throws InterruptedException {
        Random gen = new Random();
        Thread thread1 = new Thread(() -> {
            for (int n = 1; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread2 = new Thread(() -> {
            for (int n = 2; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread3 = new Thread(() -> {
            for (int n = 3; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread4 = new Thread(() -> {
            for (int n = 4; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread5 = new Thread(() -> {
            for (int n = 5; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread6 = new Thread(() -> {
            for (int n = 6; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread7 = new Thread(() -> {
            for (int n = 7; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        Thread thread8 = new Thread(() -> {
            for (int n = 8; n < 101; n += 8)
                try {
                    simulate(n * 1000, gen);
                } catch (java.io.IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

    }
}
