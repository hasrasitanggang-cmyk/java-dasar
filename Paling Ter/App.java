import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> values = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.equals("---")) break;
            if (line.isEmpty()) continue;
            values.add(Integer.parseInt(line));
        }

        if (values.isEmpty()) {
            return; // tidak ada output
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : values) {
            freq.merge(v, 1, Integer::sum);
        }

        int tertinggi = values.get(0);
        int terendah = values.get(0);
        for (int v : values) {
            if (v > tertinggi) tertinggi = v;
            if (v < terendah) terendah = v;
        }

        int terbanyakVal = 0, terbanyakFreq = -1;
        int tersedikitVal = 0, tersedikitFreq = Integer.MAX_VALUE;
        long jumlahTertinggiVal = 0, jumlahTertinggiProduk = Long.MIN_VALUE;
        long jumlahTerendahVal = 0, jumlahTerendahProduk = Long.MAX_VALUE;

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int val = e.getKey();
            int f = e.getValue();

            // Terbanyak: frekuensi tertinggi, seri -> nilai lebih besar
            if (f > terbanyakFreq || (f == terbanyakFreq && val > terbanyakVal)) {
                terbanyakFreq = f;
                terbanyakVal = val;
            }

            // Tersedikit: frekuensi terendah, seri -> nilai lebih kecil
            if (f < tersedikitFreq || (f == tersedikitFreq && val < tersedikitVal)) {
                tersedikitFreq = f;
                tersedikitVal = val;
            }

            long produk = (long) val * f;

            // Jumlah Tertinggi: produk terbesar, seri -> nilai lebih besar
            if (produk > jumlahTertinggiProduk || (produk == jumlahTertinggiProduk && val > jumlahTertinggiVal)) {
                jumlahTertinggiProduk = produk;
                jumlahTertinggiVal = val;
            }

            // Jumlah Terendah: produk terkecil, seri -> nilai lebih kecil
            if (produk < jumlahTerendahProduk || (produk == jumlahTerendahProduk && val < jumlahTerendahVal)) {
                jumlahTerendahProduk = produk;
                jumlahTerendahVal = val;
            }
        }

        int terbanyakF = freq.get(terbanyakVal);
        int tersedikitF = freq.get(tersedikitVal);
        int jtF = freq.get((int) jumlahTertinggiVal);
        int jtrF = freq.get((int) jumlahTerendahVal);

        System.out.println("Tertinggi: " + tertinggi);
        System.out.println("Terendah: " + terendah);
        System.out.println("Terbanyak: " + terbanyakVal + " (" + terbanyakF + "x)");
        System.out.println("Tersedikit: " + tersedikitVal + " (" + tersedikitF + "x)");
        System.out.println("Jumlah Tertinggi: " + jumlahTertinggiVal + " * " + jtF + " = " + (jumlahTertinggiVal * jtF));
        System.out.println("Jumlah Terendah: " + jumlahTerendahVal + " * " + jtrF + " = " + (jumlahTerendahVal * jtrF));
    }
}
