import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * Program sederhana perhitungan gaji pokok dan gaji lembur berdasarkan golongan
 * dan jam lembur.
 *
 * <p>
 * Ketentuan (sesuai soal):
 * <ul>
 * <li>Golongan A/B/C mengambil gaji pokok dari array gaji index 0/1/2</li>
 * <li>Persen lembur disimpan pada array: 30, 32, 34, 36, 38</li>
 * <li>Jika lembur 1/2/3/4 jam maka persen lembur 30/32/34/36</li>
 * <li>Jika lembur >= 5 jam maka persen lembur 38</li>
 * </ul>
 *
 * <p>
 * Asumsi implementasi:
 * gaji lembur per jam = (persen lembur) * gaji pokok, lalu total lembur = jam
 * lembur * gaji lembur per jam.
 */
public final class Main {
    private static final long[] GAJI_POKOK = { 5_000_000L, 6_500_000L, 9_500_000L };
    private static final int[] PERSEN_LEMBUR = { 30, 32, 34, 36, 38 };
    private static final Locale LOCALE_ID = new Locale("id", "ID");

    private Main() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println();
            String golongan = readGolongan(scanner);
            int jamLembur = readJamLembur(scanner);

            long gajiPokok = gajiPokokUntukGolongan(golongan);
            int persenLembur = persenLemburUntukJam(jamLembur);

            long lemburPerJam = (gajiPokok * persenLembur) / 100L;
            long totalLembur = lemburPerJam * jamLembur;
            long totalGaji = gajiPokok + totalLembur;

            printRingkasan(golongan, gajiPokok, jamLembur, persenLembur, lemburPerJam, totalLembur, totalGaji);
        } catch (IllegalArgumentException ex) {
            System.out.println("Input tidak valid: " + ex.getMessage());
        }
    }

    /**
     * Membaca input golongan karyawan: A, B, atau C.
     */
    private static String readGolongan(Scanner scanner) {
        System.out.print("Masukkan Golongan (A/B/C): ");
        String input = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        if (!Objects.equals(input, "A") && !Objects.equals(input, "B") && !Objects.equals(input, "C")) {
            throw new IllegalArgumentException("Golongan harus A, B, atau C.");
        }
        return input;
    }

    /**
     * Membaca input jam lembur dalam bilangan bulat (>= 0).
     */
    private static int readJamLembur(Scanner scanner) {
        System.out.print("Masukkan Jam Lembur (0-...): ");
        String raw = scanner.nextLine().trim();
        int jam;
        try {
            jam = Integer.parseInt(raw);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Jam lembur harus angka bulat.");
        }
        if (jam < 0) {
            throw new IllegalArgumentException("Jam lembur tidak boleh negatif.");
        }
        return jam;
    }

    /**
     * Menentukan gaji pokok berdasarkan golongan menggunakan array GAJI_POKOK.
     *
     * @param golongan "A", "B", atau "C"
     */
    private static long gajiPokokUntukGolongan(String golongan) {
        int index;
        if (Objects.equals(golongan, "A")) {
            index = 0;
        } else if (Objects.equals(golongan, "B")) {
            index = 1;
        } else {
            index = 2;
        }
        return GAJI_POKOK[index];
    }

    /**
     * Menentukan persen lembur berdasarkan jam lembur menggunakan array
     * PERSEN_LEMBUR.
     *
     * <p>
     * Jika jam lembur 0, persen lembur dianggap 0.
     */
    private static int persenLemburUntukJam(int jamLembur) {
        if (jamLembur <= 0) {
            return 0;
        }
        if (jamLembur == 1) {
            return PERSEN_LEMBUR[0];
        }
        if (jamLembur == 2) {
            return PERSEN_LEMBUR[1];
        }
        if (jamLembur == 3) {
            return PERSEN_LEMBUR[2];
        }
        if (jamLembur == 4) {
            return PERSEN_LEMBUR[3];
        }
        return PERSEN_LEMBUR[4];
    }

    private static void printRingkasan(
            String golongan,
            long gajiPokok,
            int jamLembur,
            int persenLembur,
            long lemburPerJam,
            long totalLembur,
            long totalGaji) {
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(LOCALE_ID);

        System.out.println();
        System.out.println("=== RINGKASAN GAJI ===");
        System.out.println("Golongan           : " + golongan);
        System.out.println("Gaji Pokok         : " + rupiah.format(gajiPokok));
        System.out.println("Jam Lembur         : " + jamLembur + " jam");
        System.out.println("Persen Lembur      : " + persenLembur + "%");
        System.out.println("Lembur per Jam     : " + rupiah.format(lemburPerJam));
        System.out.println("Total Gaji Lembur  : " + rupiah.format(totalLembur));
        System.out.println("Total Gaji         : " + rupiah.format(totalGaji));
    }
}
