class Engine{
    void start(){
        System.out.println("mesin nyala");
    }
}

class Car{
    Engine engine = new Engine();

    void drive(){
        engine.start();
        System.out.println("mobil jalan");
    }
}


public class Main2 {
    public static void main(String[] args){
        Car car = new Car();
        car.drive();
    }
    
}
