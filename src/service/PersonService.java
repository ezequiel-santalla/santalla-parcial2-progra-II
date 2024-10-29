package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.NoKitException;
import exception.TemperatureExceededException;
import model.Person;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PersonService {
    private Set<Person> personList;
    private Integer availableKits;
    private Integer actualKitNumber;
    private Map<Integer, Double> testResults;

    public PersonService(Integer availableKits) {
        this.personList = new LinkedHashSet<>();
        this.availableKits = availableKits;
        this.actualKitNumber = 1;
        this.testResults = new HashMap<>();
    }

    // Register of Personal
    public void registerPerson(String name, String lastName, Integer age, String neighborhood, Integer dni, String occupation) throws NoKitException {
        if (availableKits <= 0) {
            throw new NoKitException("No kits available");
        }

        Person person = new Person(dni, name, lastName, age, neighborhood, occupation, actualKitNumber++);

        personList.add(person);
        availableKits--;
        System.out.println(person.getName() + " registered with kit number: " + person.getKitNumber());
    }

    // Temperature test
    public void test() {
        Random random = new Random();

        for (Person person : personList) {
            Double temperature = 36 + (random.nextDouble() * 3);

            testResults.put(person.getKitNumber(), temperature);
            System.out.println("Person with kit #" + person.getKitNumber() + " tested. Temperature: " + temperature);
        }
    }

    // Isolation method
    public void isolate() {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> exceptionsList = new ArrayList<>();

        for (Person person : personList) {
            Double temperature = testResults.get(person.getKitNumber());

            if (temperature >= 38) {
                try {
                    throw new TemperatureExceededException(
                            "High temperature detected: " + temperature,
                            person.getKitNumber(),
                            person.getNeighborhood()
                    );
                } catch (TemperatureExceededException e) {
                    Map<String, Object> exceptionData = new HashMap<>();
                    exceptionData.put("Exception", e.getMessage());
                    exceptionData.put("KitNumber", e.getKitNumber());
                    exceptionData.put("Neighborhood", e.getNeighborhood());

                    exceptionsList.add(exceptionData);
                }
            }
        }

        if (!exceptionsList.isEmpty()) {
            try {
                mapper.writeValue(new File("urgent.dat"), exceptionsList);
                System.out.println("All exception data saved to urgent.dat.");
            } catch (IOException ioException) {
                System.out.println("Error saving exception data to urgent.dat: " + ioException.getMessage());
                ioException.printStackTrace();
            }
        }
    }

    // JSON report method
    public void generateJsonReport() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Map<String, Object>>> reportData = new HashMap<>();

        List<Map<String, Object>> healthyPeople = new ArrayList<>();
        List<Map<String, Object>> isolatedPeople = new ArrayList<>();

        for (Person person : personList) {
            Double temperature = testResults.get(person.getKitNumber());

            Map<String, Object> personData = new HashMap<>();
            personData.put("name", person.getName());
            personData.put("lastName", person.getLastName());
            personData.put("age", person.getAge());
            personData.put("neighborhood", person.getNeighborhood());
            personData.put("dni", person.getDni());
            personData.put("occupation", person.getOccupation());
            personData.put("kitNumber", person.getKitNumber());

            if (temperature < 38) {
                healthyPeople.add(personData);
            } else {
                Map<String, Object> isolationData = new HashMap<>(personData);
                isolationData.put("temperature", temperature);
                isolatedPeople.add(isolationData);
            }
        }

        reportData.put("healthy", healthyPeople);
        reportData.put("isolated", isolatedPeople);

        try {
            mapper.writeValue(new File("report.json"), reportData);
            System.out.println("Report saved to report.json.");
        } catch (IOException e) {
            System.out.println("Error saving report to JSON: " + e.getMessage());
        }
    }
}



