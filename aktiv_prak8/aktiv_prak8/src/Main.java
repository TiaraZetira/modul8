class Animal {

}

class Mamalia extends Animal {

}

class Reptile extends Animal {

}

class Dog extends Mamalia {

}

public class Main {
    public static void main(String[] args) {
        Animal animal1 = new Animal();
        Mamalia mamalia1 = new Mamalia();
        Dog dog1 = new Dog();
        Reptile reptile1 = new Reptile();

        System.out.println(mamalia1 instanceof Animal);
        System.out.println(dog1 instanceof Animal);
        System.out.println(dog1 instanceof Mamalia);
        //System.out.println(dog1 instanceof Reptil);

        Animal animal2 = new Dog();

        //Dog dog2 = (Dog) animal2;

        if (animal2 instanceof Dog){
            Dog dog2 = (Dog) animal2;
            System.out.println("objek berhasil di buat");
        } else {
            System.out.println("gagal membuat objek");
        }
    }
}