class Vehicle {
    public String startEngine() {
        return "Mesin Nyala";
    }
}

class Car extends Vehicle {
    public String startEngine() {
        return "Mesin Mobil Nyala";
    }
}

class Motorcycle extends Vehicle {
    public String startEngine() {
        return "Mesin Motor Nyala";
    }
}

class License {
    private String licenseNumber;
    private String validUntil;

    public License(String licenseNumber, String validUntil) {
        this.licenseNumber = licenseNumber;
        this.validUntil = validUntil;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getValidUntil() {
        return validUntil;
    }
}

class Driver {
    private String name;
    private Vehicle vehicle;
    private License license;

    public Driver(String name, Vehicle vehicle, License license) {
        this.name = name;
        this.vehicle = vehicle;
        this.license = license;
    }

    public String getName() {
        return name;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public License getLicense() {
        return license;
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle motorcycle = new Motorcycle();

        License license1 = new License("Mobil-12345", "15-05-2030");
        License license2 = new License("Motor-678910", "20-11-2029");

        Driver driver1 = new Driver("Tiara", car, license1);
        Driver driver2 = new Driver("Rippun", motorcycle, license2);

        printDriverInfo(driver1);
        printDriverInfo(driver2);
    }

    public static void printDriverInfo(Driver driver) {
        System.out.println("Nama Pengemudi: " + driver.getName());

        if (driver.getVehicle() instanceof Car) {
            System.out.println("Jenis Kendaraan: Mobil");
        } else if (driver.getVehicle() instanceof Motorcycle) {
            System.out.println("Jenis Kendaraan: Motor");
        } else {
            System.out.println("Jenis Kendaraan: Tidak diketahui");
        }

        System.out.println("Status Mesin: " + driver.getVehicle().startEngine());
        System.out.println("Nomor Lisensi: " + driver.getLicense().getLicenseNumber());
        System.out.println("Tanggal Berlaku Lisensi: " + driver.getLicense().getValidUntil());
        System.out.println("----------------------------------");
    }
}
