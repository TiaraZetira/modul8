class HealthRecord {
    private String recordNumber;
    private String creationDate;

    public HealthRecord(String recordNumber) {
        this.recordNumber = recordNumber;
        this.creationDate = getCurrentDate(); 
    }

    private String getCurrentDate() {
        return "2025-05-15"; 
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }
}

class Animal {
    protected HealthRecord healthRecord;
    protected String sound;

    public Animal(String recordNumber, String sound) {
        this.healthRecord = new HealthRecord(recordNumber);
        this.sound = sound;
    }

    public void makeSound() {
        System.out.println(sound);
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }
}

class Dog extends Animal {
    public Dog(String recordNumber) {
        super(recordNumber, "Guk-Guk!");
    }
}

class Cat extends Animal {
    public Cat(String recordNumber) {
        super(recordNumber, "Meong!");
    }
}

class Owner {
    private String name;
    private Animal pet;

    public Owner(String name, Animal pet) {
        this.name = name;
        this.pet = pet;
    }

    public void showPetInfo() {
        System.out.println("Nama Pemilik: " + name);

        System.out.print("Jenis Hewan Peliharaan: ");
        if (pet instanceof Dog) {
            System.out.println("Anjing");
        } else if (pet instanceof Cat) {
            System.out.println("Babi");
        } else {
            System.out.println("Tidak diketahui");
        }

        System.out.print("Suara Hewan Peliharaan: ");
        pet.makeSound();

        System.out.println("Nomor Rekam Medis Hewan: " + pet.getHealthRecord().getRecordNumber());
        System.out.println("Tanggal Pembuatan Rekam Medis: " + pet.getHealthRecord().getCreationDate());
    }
}

// Program utama
public class Main {
    public static void main(String[] args) {
        Animal anjing = new Dog("001");
        Animal kucing = new Cat("002");

        Owner pemilik1 = new Owner("Tiara", anjing);
        Owner pemilik2 = new Owner("Tiara", kucing);

        pemilik1.showPetInfo();
        pemilik2.showPetInfo();
    }
}
