# Janji
Saya Nezhad Ahmad Maliki dengan NIM 2503880 mengerjakan Tugas Praktikum 6 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Struktur File

```
C:.
│   .gitignore
│   flappybird.iml
│
├───.idea
│       .gitignore
│       misc.xml
│       modules.xml
│       vcs.xml
│       workspace.xml
│
├───out
│   └───production
│       └───flappybird
│           │   App.class
│           │   AudioPlayer.class
│           │   Logic$1.class
│           │   Logic.class
│           │   MainMenu.class
│           │   Pipe.class
│           │   Player.class
│           │   View.class
│           │
│           └───assets
│                   background.png
│                   Bad Piggies Theme.wav
│                   bird.png
│                   lowerPipe.png
│                   upperPipe.png
│
└───src
    │   App.java
    │   AudioPlayer.java
    │   Logic.java
    │   MainMenu.java
    │   Pipe.java
    │   Player.java
    │   View.java
    │
    └───assets
            background.png
            Bad Piggies Theme.wav
            bird.png
            lowerPipe.png
            upperPipe.png
```

# Fungsi program & Alur Program
Program Flappy Bird dibuat sebagai permainan sederhana berbasis Java Swing yang mengharuskan pemain mengendalikan seekor burung untuk melewati rintangan berupa pipa. Pemain harus menghindari tabrakan dengan pipa maupun jatuh ke bagian bawah layar untuk mendapatkan skor setinggi mungkin.

Program ini juga menerapkan peningkatan tingkat kesulitan secara bertahap berdasarkan skor yang diperoleh pemain.

### Desain Class 
Program ini menggunakan beberapa class yang memiliki fungsi dan tanggung jawab masing-masing.
- App: Class utama yang menjalankan aplikasi, mengatur JFrame, CardLayout, dan perpindahan tampilan antara menu utama dan permainan.
- MainMenu: Mengatur tampilan menu awal yang berisi judul permainan serta tombol Start dan Exit.
- Player: Menyimpan data pemain seperti posisi, ukuran, kecepatan jatuh, dan gambar burung.
- Pipe: Menyimpan data rintangan berupa posisi, ukuran, gambar pipa, status apakah sudah dilewati, dan kecepatan gerak vertikal.
- Logic: Mengatur mekanisme utama permainan, seperti gravitasi, lompatan, pergerakan pipa, deteksi tabrakan, perhitungan skor, peningkatan kesulitan, dan restart permainan.
- View: Mengatur tampilan permainan dengan menggambar background, pemain, pipa, skor, dan tampilan Game Over.
- AudioPlayer: Mengatur pemutaran musik latar serta perubahan volume ketika pemain mengalami Game Over.

### Fungsi Program
Program memiliki beberapa fungsi utama, yaitu:
1. Start Game: Memulai permainan melalui tombol Start pada menu utama.
2. Player Movement: Menggerakkan burung menggunakan tombol Space untuk melompat dan gravitasi untuk membuat burung jatuh.
3. Pipe Movement: Menggerakkan pipa dari kanan ke kiri sebagai rintangan yang harus dilewati pemain.
4. Score System: Menambahkan satu skor setiap kali pemain berhasil melewati sepasang pipa.
5. Difficulty System: Meningkatkan kecepatan permainan sebanyak 1,5 kali setiap kelipatan 20 skor.
6. Moving Pipes: Membuat pipa bergerak naik dan turun ketika skor sudah mencapai lebih dari 50.
7. Game Over: Mengakhiri permainan ketika burung menabrak pipa atau jatuh melewati batas bawah layar.
8. Restart Game: Memulai ulang permainan menggunakan tombol R setelah Game Over.
9. Audio System: Memutar musik latar selama permainan dan menurunkan volumenya ketika pemain mengalami Game Over.
10. Exit: Menghentikan aplikasi melalui tombol Exit pada menu utama.

### Design Program
Program menggunakan Java Swing untuk membangun antarmuka permainan. Komponen yang digunakan antara lain:
- JFrame: Sebagai jendela utama aplikasi.
- JPanel: Sebagai tempat untuk menampilkan menu utama dan area permainan.
- CardLayout: Mengatur perpindahan tampilan antara menu utama dan permainan.
- JButton: Digunakan untuk tombol Start dan Exit.
- Graphics: Digunakan untuk menggambar background, burung, pipa, skor, dan tampilan Game Over.
- Timer: Mengatur pembaruan pergerakan objek permainan secara berkala.
- ImageIcon: Digunakan untuk memuat gambar burung, background, dan pipa.
- Clip: Digunakan untuk memutar musik latar menggunakan Java Sound.
Pada tampilan permainan, skor ditampilkan langsung di bagian atas background menggunakan objek Graphics, sehingga tidak membutuhkan JLabel terpisah.

### Alur Program
Alur program dimulai ketika pengguna menjalankan aplikasi dan masuk ke tampilan Main Menu. Pada tampilan tersebut, pengguna diberikan dua pilihan, yaitu Start untuk memulai permainan dan Exit untuk menutup aplikasi.

Ketika pengguna menekan tombol Start, class App akan membuat objek Logic dan View, kemudian menampilkan area permainan. Class Logic mengatur seluruh mekanisme permainan, sedangkan class View bertugas menggambar objek permainan ke layar. Setelah permainan dimulai, musik latar akan diputar melalui class AudioPlayer.

Selama permainan berlangsung, pengguna dapat menekan tombol Space untuk membuat burung melompat. Pergerakan burung dipengaruhi oleh gravitasi yang membuatnya terus jatuh ketika tidak melakukan lompatan. Pada saat yang sama, pipa akan muncul secara berkala dari sebelah kanan layar dan bergerak ke arah kiri.

Ketika burung berhasil melewati sepasang pipa, skor akan bertambah satu. Setiap skor mencapai kelipatan 20, kecepatan pipa, gravitasi, dan kekuatan lompatan akan meningkat sebanyak 1,5 kali dari tingkat sebelumnya. Selain itu, apabila skor sudah lebih dari 50, pipa akan mulai bergerak naik dan turun sehingga tingkat kesulitan permainan semakin meningkat.

Program juga melakukan pemeriksaan tabrakan antara burung dan pipa menggunakan objek Rectangle dan method intersects(). Apabila burung menabrak pipa atau jatuh melewati batas bawah layar, permainan akan berakhir. Class Logic kemudian menghentikan timer permainan dan menurunkan volume musik latar. Tampilan Game Over akan ditampilkan melalui class View, lengkap dengan skor akhir dan instruksi untuk melakukan restart.

Jika pengguna menekan tombol R, posisi burung, skor, kecepatan permainan, gravitasi, dan kekuatan lompatan akan dikembalikan ke kondisi awal. Daftar pipa juga dikosongkan dan musik latar diputar kembali dari awal. Dengan demikian, pengguna dapat memainkan permainan dari awal tanpa harus menutup aplikasi.

### Penggunaan Algoritma dan Sistem Permainan
Program menggunakan beberapa mekanisme logika untuk mengatur jalannya permainan, yaitu:
- Gravitasi: Mengatur percepatan jatuh burung secara bertahap.
- Collision Detection: Memeriksa apakah area burung bertabrakan dengan area pipa.
- Score Calculation: Menghitung skor berdasarkan jumlah pipa yang berhasil dilewati.
- Difficulty Scaling: Menggunakan speedMultiplier dengan perhitungan pangkat 1,5 untuk meningkatkan kecepatan permainan setiap 20 skor.
- Vertical Pipe Movement: Mengubah arah gerakan pipa ketika mencapai batas atas atau bawah yang telah ditentukan.
  
Sistem tersebut membuat permainan memiliki tingkat kesulitan yang terus meningkat seiring bertambahnya skor pemain.

# Dokumentasi

## Tampilan Awal
<img src="Dokumentasi/tampilan awal.gif" width="100%">

## Kalo Mati
<img src="Dokumentasi/klo mati.gif" width="100%">

## Tampilan Restart
<img src="Dokumentasi/klo restart.gif" width="100%">
