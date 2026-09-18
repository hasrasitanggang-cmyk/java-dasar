import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.hasNextLine() ? sc.nextLine().trim() : "";
        String[] parts = first.split(":", -1);

        boolean valid = true;
        int hour = 0, minute = 0;

        if (parts.length != 2) {
            valid = false;
        } else {
            try {
                hour = Integer.parseInt(parts[0].trim());
                minute = Integer.parseInt(parts[1].trim());
                if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                    valid = false;
                }
            } catch (NumberFormatException e) {
                valid = false;
            }
        }

        if (!valid) {
            System.out.println("Jam tidak valid");
            return;
        }

        String jamAwal = String.format("%02d:%02d", hour, minute);
        int totalMenitWaktu = hour * 60 + minute;
        int totalGeser = 0;
        int pergantianHari = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;

            char tanda = line.charAt(0);
            if (tanda != '+' && tanda != '-') {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int n;
            try {
                n = Integer.parseInt(line.substring(1));
            } catch (NumberFormatException e) {
                System.out.println("Perintah tidak valid");
                continue;
            }

            int delta = (tanda == '+') ? n : -n;
            totalGeser += delta;
            totalMenitWaktu += delta;

            while (totalMenitWaktu >= 1440) {
                totalMenitWaktu -= 1440;
                pergantianHari++;
            }
            while (totalMenitWaktu < 0) {
                totalMenitWaktu += 1440;
                pergantianHari++;
            }
        }

        int jamAkhirH = totalMenitWaktu / 60;
        int jamAkhirM = totalMenitWaktu % 60;
        String jamAkhir = String.format("%02d:%02d", jamAkhirH, jamAkhirM);

        String totalMenitStr = (totalGeser > 0) ? ("+" + totalGeser) : String.valueOf(totalGeser);

        System.out.println("Jam Awal: " + jamAwal);
        System.out.println("Jam Akhir: " + jamAkhir);
        System.out.println("Total Menit: " + totalMenitStr);
        System.out.println("Pergantian Hari: " + pergantianHari);
    }
}
