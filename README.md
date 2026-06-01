# LemburKaryawanJava3

Tugas 3 STSI4102 ALGORITMA DAN PEMROGRAMAN

Identitas:

- Nama: Yosada Dede Aressa
- NIM: 052984188

Program Java sederhana untuk menghitung gaji pokok dan gaji lembur berdasarkan:

- Golongan karyawan: A, B, C (diambil dari array gaji pokok)
- Jam lembur
- Persentase lembur: 30%, 32%, 34%, 36%, 38% (diambil dari array persen lembur)

## Aturan Soal

- Array gaji pokok:
  - Golongan A: Rp 5.000.000 (index 0)
  - Golongan B: Rp 6.500.000 (index 1)
  - Golongan C: Rp 9.500.000 (index 2)
- Array persen lembur: 30, 32, 34, 36, 38
- Jika lembur:
  - 1 jam → 30%
  - 2 jam → 32%
  - 3 jam → 34%
  - 4 jam → 36%
  - >= 5 jam → 38%

Implementasi di kode:

- Lembur per jam = (persen lembur / 100) × gaji pokok
- Total lembur = jam lembur × lembur per jam
- Total gaji = gaji pokok + total lembur

## Cara Menjalankan

Pastikan sudah ter-install JDK (Java Development Kit).

1. Masuk ke folder project:

```bash
cd /Users/macbookpro/Tugas/LemburKaryawanJava3
```

2. Compile:

```bash
javac Main.java
```

3. Run:

```bash
java Main
```

## Contoh Input/Output

Input:

- Golongan: A
- Jam lembur: 2

Output (contoh):

- Gaji Pokok: Rp5.000.000,00
- Persen Lembur: 32%
- Lembur per Jam: Rp1.600.000,00
- Total Gaji Lembur: Rp3.200.000,00
- Total Gaji: Rp8.200.000,00
