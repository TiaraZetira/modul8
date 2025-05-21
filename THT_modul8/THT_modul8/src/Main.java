import java.util.ArrayList;
import java.util.Scanner;

class Akun {
    String username;
    String password;
    String role;

    Akun(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

class Potion {
    String jenis;

    Potion(String jenis) {
        this.jenis = jenis;
    }

    void gunakan(Pemain pemain) {
        pemain.darah += 50;
        pemain.mana += 10;
        System.out.println("Kamu menggunakan potion: " + jenis);
    }
}

class Ransel {
    String[] barang = new String[5];

    void tambahItem(String item) {
        for (int i = 0; i < barang.length; i++) {
            if (barang[i] == null) {
                barang[i] = item;
                System.out.println("Item diperoleh: " + item);
                return;
            }
        }
        System.out.println("Ranselmu penuh!");
    }

    void tampilkanIsi() {
        System.out.println(">> Isi Ransel:");
        for (int i = 0; i < barang.length; i++) {
            System.out.println((i + 1) + ". " + (barang[i] == null ? "Kosong" : barang[i]));
        }
    }
}

class Pemain {
    String nama;
    double darah = 100;
    double mana = 100;
    int level = 1;
    Ransel ransel = new Ransel();
    ArrayList<Potion> kantongPotion = new ArrayList<>();

    Pemain(String nama) {
        this.nama = nama;
    }

    void gunakanPotion() {
        if (!kantongPotion.isEmpty()) {
            Potion potion = kantongPotion.remove(0);
            potion.gunakan(this);
        } else {
            System.out.println("Tidak ada potion tersisa.");
        }
    }

    void tampilkanStatus() {
        System.out.println(">> Status Pemain:");
        System.out.println("Nama: " + nama);
        System.out.println("Darah: " + darah);
        System.out.println("Mana: " + mana);
        System.out.println("Level: " + level);
    }

    void bukaRansel() {
        tampilkanStatus();
        System.out.println("Jumlah Potion: " + kantongPotion.size());
        ransel.tampilkanIsi();
    }

    void tambahItem(String item) {
        ransel.tambahItem(item);
    }

    void tambahPotion(Potion p) {
        kantongPotion.add(p);
        System.out.println("Potion '" + p.jenis + "' dimasukkan ke kantong.");
    }
}

class Penyihir extends Pemain {
    Penyihir(String nama) {
        super(nama);
        this.mana = 150;
    }

    @Override
    void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("Role: Penyihir");
    }
}

class Petarung extends Pemain {
    Petarung(String nama) {
        super(nama);
        this.darah = 150;
    }

    @Override
    void tampilkanStatus() {
        super.tampilkanStatus();
        System.out.println("Role: Petarung");
    }
}

class Musuh {
    String nama;
    double darah;
    double mana;
    double serangan;

    Musuh(String nama, double darah, double mana, double serangan) {
        this.nama = nama;
        this.darah = darah;
        this.mana = mana;
        this.serangan = serangan;
    }

    void tampilkanInfo() {
        System.out.println("-- Musuh Ditemukan --");
        System.out.println("Nama: " + nama);
        System.out.println("Darah: " + darah);
        System.out.println("Mana: " + mana);
        System.out.println("Serangan: " + serangan);
    }
}

class GameManager {
    private Scanner input = new Scanner(System.in);
    private Akun[] akunList = new Akun[100];
    private int jumlahAkun = 0;

    void run() {
        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int opsi = input.nextInt(); input.nextLine();

            switch (opsi) {
                case 1:
                    Akun loginAkun = login();
                    if (loginAkun != null) {
                        Pemain pemain = buatPemain(loginAkun);
                        pemain.tambahPotion(new Potion("Healing"));
                        pemain.tambahPotion(new Potion("Healing"));
                        mulaiPermainan(pemain);
                    }
                    break;
                case 2:
                    registrasi();
                    break;
                case 3:
                    System.out.println("Terima kasih telah bermain!");
                    return;
                default:
                    System.out.println("Opsi tidak dikenal.");
            }
        }
    }

    private void registrasi() {
        System.out.print("Buat username: ");
        String user = input.nextLine();
        System.out.print("Buat password: ");
        String pass = input.nextLine();

        akunList[jumlahAkun++] = new Akun(user, pass, null);
        System.out.println("Akun berhasil dibuat. Silakan login.");
    }

    private Akun login() {
        System.out.print("Username: ");
        String user = input.nextLine();
        System.out.print("Password: ");
        String pass = input.nextLine();

        for (int i = 0; i < jumlahAkun; i++) {
            if (akunList[i].username.equals(user) && akunList[i].password.equals(pass)) {
                System.out.println("Login sukses!");
                if (akunList[i].role == null) {
                    System.out.println("Pilih Role:");
                    System.out.println("1. Penyihir");
                    System.out.println("2. Petarung");
                    System.out.print("Pilihan: ");
                    String pilihan = input.nextLine();
                    akunList[i].role = pilihan.equals("1") ? "Penyihir" : "Petarung";
                    System.out.println("Role dipilih: " + akunList[i].role);
                }
                return akunList[i];
            }
        }
        System.out.println("Login gagal. Coba lagi.");
        return null;
    }

    private Pemain buatPemain(Akun akun) {
        return akun.role.equals("Penyihir") ? new Penyihir(akun.username) : new Petarung(akun.username);
    }

    private void mulaiPermainan(Pemain pemain) {
        while (true) {
            System.out.println("\n=== MENU GAME ===");
            System.out.println("1. Cek Ransel");
            System.out.println("2. Mulai Petualangan");
            System.out.println("3. Gunakan Potion");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            int opsi = input.nextInt();

            switch (opsi) {
                case 1:
                    pemain.bukaRansel();
                    break;
                case 2:
                    petualangan(pemain);
                    break;
                case 3:
                    pemain.gunakanPotion();
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama...");
                    return;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }

    private void petualangan(Pemain pemain) {
        while (true) {
            System.out.println("\n--- PETUALANGAN ---");
            System.out.println("Ke mana kamu melangkah?");
            System.out.println("1. Maju");
            System.out.println("2. Mundur");
            System.out.println("3. Kiri");
            System.out.println("4. Kanan");
            System.out.println("5. Kembali");
            System.out.print("Langkah: ");
            int arah = input.nextInt();

            if (arah == 5) break;

            double chance = Math.random();
            if (chance < 0.25) {
                String[] items = {"Pedang", "Perisai", "Koin Emas", "Potion Mini"};
                String item = items[(int)(Math.random() * items.length)];
                pemain.tambahItem(item);
            } else if (chance < 0.5) {
                pemain.tambahPotion(new Potion("Healing"));
            } else if (chance < 0.75) {
                System.out.println("Tidak terjadi apa-apa di jalur ini.");
            } else {
                Musuh musuh = new Musuh("Goblin", 50, 20, 15);
                System.out.println("Musuh menghadang!");
                musuh.tampilkanInfo();
                while (musuh.darah > 0 && pemain.darah > 0) {
                    System.out.println("1. Serang");
                    System.out.println("2. Kabur");
                    System.out.print("Aksi: ");
                    int aksi = input.nextInt();

                    if (aksi == 1) {
                        double damage = 20;
                        musuh.darah -= damage;
                        System.out.println("Kamu menyerang sebesar " + damage);
                        if (musuh.darah > 0) {
                            pemain.darah -= musuh.serangan;
                            System.out.println("Musuh menyerang balik! Darahmu tersisa " + pemain.darah);
                        } else {
                            System.out.println("Musuh berhasil dikalahkan!");
                            pemain.level++;
                            pemain.tambahPotion(new Potion("Healing"));
                        }
                    } else {
                        System.out.println("Kamu melarikan diri!");
                        break;
                    }
                }
                if (pemain.darah <= 0) {
                    System.out.println("Kamu tumbang dalam pertempuran...");
                    break;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager();
        game.run();
    }
}
