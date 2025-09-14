// Car class is implemented using Builder pattern + Clean Code principles
public final class Car {
    private final String make;
    private final String model;
    private final String engine;
    private final String color;
    private final int seats;
    // Car is immutable: all fields are final and constructor is private

    private Car(Builder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.engine = builder.engine;
        this.color = builder.color;
        this.seats = builder.seats;
    }

    // SRP: Car only stores data, Builder takes care of creating the object
    public static Builder builder(String make, String model) {
        return new Builder(make, model);
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", color='" + color + '\'' +
                ", seats=" + seats +
                '}';
    }

    // Nested static class Builder builds Car step by step
    public static final class Builder {
        private final String make;
        private final String model;
        private String engine = "I4";
        private String color = "Black";
        private int seats = 4; // default values for optional fields

        // Builder requires mandatory fields (make and model)
        public Builder(String make, String model) {
            this.make = requireNonBlank(make, "make");
            this.model = requireNonBlank(model, "model");
        }

        // Each method configures only one field small and simple methods
        public Builder withEngine(String engine) {
            this.engine = requireNonBlank(engine, "engine");
            return this;
        }

        public Builder withColor(String color) {
            this.color = requireNonBlank(color, "color");
            return this;
        }

        public Builder withSeats(int seats) {
            if (seats <= 0) throw new IllegalArgumentException("seats must be > 0");
            this.seats = seats;
            return this;
        }
        // Fluent API: allows chaining like builder.withX().withY().build()

        // Final step return ready Car object
        public Car build() {
            return new Car(this);
        }

        // Separate method for validation avoids code duplication (DRY)
        private static String requireNonBlank(String value, String fieldName) {
            if (value == null || value.trim().isEmpty()) {
                throw new IllegalArgumentException(fieldName + " must not be blank");
            }
            return value.trim();
        }
    }
}
