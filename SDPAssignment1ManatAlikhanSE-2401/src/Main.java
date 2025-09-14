public class Main {
    public static void main(String[] args) {
        Car car = Car.builder("Toyota", "Camry")
                .withEngine("V12")
                .withColor("White")
                .withSeats(5)
                .build();

        System.out.println(car);

        // Example with defaults (engine=I4, color=Black, seats=4)
        Car defaultCar = Car.builder("Mercedes", "Benz").build();
        System.out.println(defaultCar);
    }
}
