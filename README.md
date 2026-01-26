<h1 align="center"><b>App Pengelola Buku LKS</b></h1>

<p align="center">
  <img src="assets/icons/java.svg" height="20" />
  <img src="https://img.shields.io/badge/Java-Swing%20GUI-orange?style=flat-square" />&nbsp;
  <img src="assets/icons/mysql.svg" height="20" />
  <img src="https://img.shields.io/badge/MySQL-Database-lightblue?style=flat-square" />&nbsp;
  <img src="assets/icons/jasper.svg" height="20" />
  <img src="https://img.shields.io/badge/JasperReports-Reporting-green?style=flat-square" />&nbsp;
  <img src="assets/icons/netbeans.svg" height="20" />
  <img src="https://img.shields.io/badge/NetBeans-IDE-darkblue?style=flat-square" />
  <br>
  <img src="https://img.shields.io/github/repo-size/namauser/App-Pengelola-Buku-Lks" />
  <img src="https://img.shields.io/github/last-commit/namauser/App-Pengelola-Buku-Lks" />
  <img src="https://img.shields.io/badge/platform-Windows%20%7C%20Linux%20%7C%20macOS-lightgrey" />
</p>

Aplikasi **App Pengelola Buku LKS** adalah aplikasi **desktop berbasis Java** yang dirancang untuk membantu **pengelolaan data buku LKS (Lembar Kerja Siswa)** secara rapi, cepat, dan terstruktur.

Aplikasi ini cocok digunakan oleh **sekolah, koperasi sekolah, admin gudang buku, maupun staff tata usaha** yang membutuhkan sistem pencatatan buku yang mudah digunakan.

---

## 🔖 Informasi Umum

- **Jenis Aplikasi** : Desktop Application
- **Platform** : Windows / Linux / macOS
- **Target Pengguna** : Admin, Operator, Staff Sekolah
- **Fokus** : Pengelolaan data & laporan buku LKS

---

## 🛠️ Teknologi & Tools yang Digunakan

### 💻 Bahasa & Platform

- ☕ **Java** (Desktop Application)

### 🎨 Antarmuka

- Java Swing (GUI)

### 📊 Laporan

- JasperReports

### 🧰 Tools Development

- Apache NetBeans IDE
- Java Development Kit (JDK)

### 🗄️ Database

- MySQL

---

## ✨ Fitur Utama Aplikasi

- 📘 Menampilkan data buku LKS
- ➕ Menambahkan data buku baru
- ✏️ Mengubah / memperbarui data buku
- 🗑️ Menghapus data buku
- 🔍 Pencarian data buku
- 📄 Cetak & generate laporan buku
- 🖥️ Tampilan desktop sederhana dan mudah digunakan

---

## 🖼️ Dokumentasi Tampilan Aplikasi

### Login Frame

![Login Frame](assets/profile/loginframe.svg)

### Menu Utama Frame

![Menu Utama Frame](assets/profile/menuframe.svg)

---

## 🚀 Cara Menjalankan Aplikasi (Step-by-Step)

### 1️⃣ Persiapan Lingkungan

Pastikan software berikut sudah terinstall:

- Java JDK
- Apache NetBeans IDE
- MySQL Server

---

### 2️⃣ Setup Database

1. Jalankan MySQL Server
2. Buat database baru
3. Import file database (`.sql`) jika tersedia
4. Sesuaikan konfigurasi database di source code:
   - Host
   - Username
   - Password
   - Nama database

---

### 3️⃣ Membuka Project

1. Buka **Apache NetBeans**
2. Pilih menu **Open Project**
3. Arahkan ke folder:
   ```text
   App-Pengelola-Buku-Lks/
   ```

---

### 4️⃣ Menjalankan Aplikasi

- Klik tombol **Run Project** di NetBeans
- Atau jalankan class `Main.java`

Jika menggunakan file `.jar`:

```bash
java -jar NamaAplikasi.jar
```

---

## 🧩 Struktur Project (Ringkas)

```text
App-Pengelola-Buku-Lks/
├── src/              # Source code Java
├── nbproject/        # Konfigurasi NetBeans
├── dist/             # Output file .jar
├── reports/          # File JasperReports
├── screenshots/      # Dokumentasi gambar aplikasi
└── README.md
```

---

## 🎯 Kegunaan Aplikasi

- Membantu pengelolaan data buku LKS secara digital
- Mengurangi kesalahan pencatatan manual
- Mempercepat pencarian data buku
- Memudahkan pembuatan laporan
- Data tersimpan rapi di database

---

## 📌 Catatan Penggunaan

- Pastikan koneksi database aktif sebelum menjalankan aplikasi
- Gunakan format data yang sesuai saat input
- Lakukan backup database secara berkala

---

## 👨‍💻 Author

Dikembangkan oleh **[Nama Kamu]**

Jika repository ini bermanfaat, jangan lupa beri ⭐ di GitHub.

---

> README ini dibuat dengan gaya **friendly, ringkas, dan mudah dipahami**, cocok untuk pengguna maupun keperluan portfolio GitHub.
