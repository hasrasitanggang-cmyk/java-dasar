import java.util.Scanner;
import java.util.StringTokenizer;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Kasus khusus: 1x1
        if (n == 1) {
            int v = matrix[0][0];
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + v);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + v);
            return;
        }

        // Kasus khusus: 2x2
        if (n == 2) {
            int total = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    total += matrix[i][j];
                }
            }
            System.out.println("Nilai L: Tidak Ada");
            System.out.println("Nilai Kebalikan L: Tidak Ada");
            System.out.println("Nilai Tengah: " + total);
            System.out.println("Perbedaan: Tidak Ada");
            System.out.println("Dominan: " + total);
            return;
        }

        // n >= 3
        int nilaiL = 0;
        for (int i = 0; i < n; i++) {
            nilaiL += matrix[i][0]; // seluruh kolom pertama
        }
        for (int j = 1; j <= n - 2; j++) {
            nilaiL += matrix[n - 1][j]; // baris terakhir, tanpa pojok kanan bawah
        }

        int nilaiKebalikanL = 0;
        for (int i = 0; i < n; i++) {
            nilaiKebalikanL += matrix[i][n - 1]; // seluruh kolom terakhir
        }
        for (int j = 1; j <= n - 2; j++) {
            nilaiKebalikanL += matrix[0][j]; // baris pertama, tanpa pojok kiri atas
        }

        int nilaiTengah;
        if (n % 2 == 1) {
            nilaiTengah = matrix[n / 2][n / 2];
        } else {
            int a = n / 2 - 1;
            int b = n / 2;
            nilaiTengah = matrix[a][a] + matrix[a][b] + matrix[b][a] + matrix[b][b];
        }

        int perbedaan = Math.abs(nilaiL - nilaiKebalikanL);
        int dominan = (perbedaan == 0) ? nilaiTengah : Math.max(nilaiL, nilaiKebalikanL);

        System.out.println("Nilai L: " + nilaiL);
        System.out.println("Nilai Kebalikan L: " + nilaiKebalikanL);
        System.out.println("Nilai Tengah: " + nilaiTengah);
        System.out.println("Perbedaan: " + perbedaan);
        System.out.println("Dominan: " + dominan);
    }
}
