import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bobotPA = Integer.parseInt(sc.nextLine().trim());
        int bobotT = Integer.parseInt(sc.nextLine().trim());
        int bobotK = Integer.parseInt(sc.nextLine().trim());
        int bobotP = Integer.parseInt(sc.nextLine().trim());
        int bobotUTS = Integer.parseInt(sc.nextLine().trim());
        int bobotUAS = Integer.parseInt(sc.nextLine().trim());

        int totalBobot = bobotPA + bobotT + bobotK + bobotP + bobotUTS + bobotUAS;
        if (totalBobot != 100) {
            System.out.println("Total bobot harus 100");
            return;
        }

        int totalPA = 0, totalT = 0, totalK = 0, totalP = 0, totalUTS = 0, totalUAS = 0;
        int perolehanPA = 0, perolehanT = 0, perolehanK = 0, perolehanP = 0, perolehanUTS = 0, perolehanUAS = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().equals("---")) break;

            String[] parts = line.split("\\|", -1);
            if (parts.length != 3) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            String simbol = parts[0].trim();
            int bobotKomponen;
            int perolehanKomponen;
            try {
                bobotKomponen = Integer.parseInt(parts[1].trim());
                perolehanKomponen = Integer.parseInt(parts[2].trim());
            } catch (NumberFormatException e) {
                System.out.println("Data tidak valid. Silahkan menggunakan format: Simbol|Bobot|Perolehan-Nilai");
                continue;
            }

            if (!isSimbolDikenal(simbol)) {
                System.out.println("Simbol tidak dikenal");
                continue;
            }

            // Clamp perolehan ke rentang [0, bobotKomponen]
            if (perolehanKomponen > bobotKomponen) perolehanKomponen = bobotKomponen;
            if (perolehanKomponen < 0) perolehanKomponen = 0;

            switch (simbol) {
                case "PA":
                    totalPA += bobotKomponen; perolehanPA += perolehanKomponen; break;
                case "T":
                    totalT += bobotKomponen; perolehanT += perolehanKomponen; break;
                case "K":
                    totalK += bobotKomponen; perolehanK += perolehanKomponen; break;
                case "P":
                    totalP += bobotKomponen; perolehanP += perolehanKomponen; break;
                case "UTS":
                    totalUTS += bobotKomponen; perolehanUTS += perolehanKomponen; break;
                case "UAS":
                    totalUAS += bobotKomponen; perolehanUAS += perolehanKomponen; break;
            }
        }

        int persenPA = hitungPersen(perolehanPA, totalPA);
        int persenT = hitungPersen(perolehanT, totalT);
        int persenK = hitungPersen(perolehanK, totalK);
        int persenP = hitungPersen(perolehanP, totalP);
        int persenUTS = hitungPersen(perolehanUTS, totalUTS);
        int persenUAS = hitungPersen(perolehanUAS, totalUAS);

        double kontribusiPA = (persenPA / 100.0) * bobotPA;
        double kontribusiT = (persenT / 100.0) * bobotT;
        double kontribusiK = (persenK / 100.0) * bobotK;
        double kontribusiP = (persenP / 100.0) * bobotP;
        double kontribusiUTS = (persenUTS / 100.0) * bobotUTS;
        double kontribusiUAS = (persenUAS / 100.0) * bobotUAS;

        double nilaiAkhir = kontribusiPA + kontribusiT + kontribusiK + kontribusiP + kontribusiUTS + kontribusiUAS;
        nilaiAkhir = Math.round(nilaiAkhir * 100.0) / 100.0;

        System.out.println("Perolehan Nilai:");
        cetakKomponen("Partisipatif", persenPA, kontribusiPA, bobotPA);
        cetakKomponen("Tugas", persenT, kontribusiT, bobotT);
        cetakKomponen("Kuis", persenK, kontribusiK, bobotK);
        cetakKomponen("Proyek", persenP, kontribusiP, bobotP);
        cetakKomponen("UTS", persenUTS, kontribusiUTS, bobotUTS);
        cetakKomponen("UAS", persenUAS, kontribusiUAS, bobotUAS);
        System.out.println();
        System.out.printf(">> Nilai Akhir: %.2f%n", nilaiAkhir);
        System.out.println(">> Grade: " + getGrade(nilaiAkhir));
    }

    private static boolean isSimbolDikenal(String simbol) {
        return simbol.equals("PA") || simbol.equals("T") || simbol.equals("K")
                || simbol.equals("P") || simbol.equals("UTS") || simbol.equals("UAS");
    }

    private static int hitungPersen(int perolehan, int total) {
        if (total == 0) return 0;
        return (perolehan * 100) / total;
    }

    private static void cetakKomponen(String nama, int persen, double kontribusi, int bobot) {
        System.out.printf(">> %s: %d/100 (%.2f/%d)%n", nama, persen, kontribusi, bobot);
    }

    private static String getGrade(double nilai) {
        if (nilai >= 79.5) return "A";
        if (nilai >= 72) return "AB";
        if (nilai >= 64.5) return "B";
        if (nilai >= 57) return "BC";
        if (nilai >= 49.5) return "C";
        if (nilai >= 34) return "D";
        return "E";
    }
}