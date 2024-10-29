package model;

import java.util.Objects;

public class Person {
    private String name;
    private String lastName;
    private Integer age;
    private String neighborhood;
    private Integer dni;
    private String occupation;
    private Integer kitNumber;

    public Person() {
    }

    public Person(Integer dni, String name, String lastName, Integer age, String neighborhood, String occupation, Integer kitNumber) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.neighborhood = neighborhood;
        this.occupation = occupation;
        this.kitNumber = kitNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getKitNumber() {
        return kitNumber;
    }

    public void setKitNumber(Integer kitNumber) {
        this.kitNumber = kitNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(age, person.age) && Objects.equals(neighborhood, person.neighborhood) && Objects.equals(dni, person.dni) && Objects.equals(occupation, person.occupation) && Objects.equals(kitNumber, person.kitNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age, neighborhood, dni, occupation, kitNumber);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", neighborhood='" + neighborhood + '\'' +
                ", dni=" + dni +
                ", occupation='" + occupation + '\'' +
                ", kitNumber=" + kitNumber +
                '}';
    }
}
