import exception.NoKitException;
import service.PersonService;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonService(5);

        // Adding people to the list
        try {
            personService.registerPerson("Juan", "Pérez", 30, "Downtown", 12345678, "Engineer");
            personService.registerPerson("Juana", "Gómez", 25, "Uptown", 87654321, "Doctor");
            personService.registerPerson("Alicia", "López", 35, "Midtown", 11223344, "Nurse");
            personService.registerPerson("Roberto", "Martínez", 28, "Eastside", 44556677, "Teacher");
            personService.registerPerson("Carlos", "Díaz", 40, "Westside", 99887766, "Chef");
            personService.registerPerson("Eva", "Ramírez", 32, "Northside", 55667788, "Artist");
        } catch (NoKitException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();

        // Testing the temperatures
        System.out.println("Testing the temperatures...");
        System.out.println();
        personService.test();

        // Trying isolation and catching the exception
        System.out.println();
        personService.isolate();

        // Generate JSON report with healthy and isolated people
        System.out.println();
        personService.generateJsonReport();
    }
}
