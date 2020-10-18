package streams_feature;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    var people = List.of(
        new Person("Alex", 26, Person.Gender.MALE),
        new Person("Vladimir", 35, Person.Gender.MALE),
        new Person("Sofia", 28, Person.Gender.FEMALE),
        new Person("Anna", 56, Person.Gender.FEMALE),
        new Person("Nick", 63, Person.Gender.MALE)
    );

    // filter
    System.out.println();
    System.out.println("Display all people who is older than 35 years");
    people
        .stream()
        .filter(person -> person.getAge() > 35)
        .forEach(System.out::println);

    // filter
    System.out.println();
    System.out.println("Display all men");
    people
        .stream()
        .filter(person -> person.getGender().equals(Person.Gender.MALE))
        .forEach(System.out::println);

    // sorted
    System.out.println();
    System.out.println("Display all people sorted by name");
    people
        .stream()
        .sorted(Comparator.comparing(Person::getName))
        .forEach(System.out::println);

    // map
    System.out.println();
    System.out.println("Set the same age for each person: 25 years");
    people
        .stream()
        .map(person -> {
          person.setAge(25);
          return person;
        })
        .forEach(System.out::println);

    // collect -> groupingBy
    System.out.println();
    System.out.println("Display all person grouped by gender");
    Map<Person.Gender, List<Person>> genderPeopleMap = people
        .stream()
        .collect(Collectors.groupingBy(Person::getGender));
    genderPeopleMap.forEach((gender, p) -> System.out.println("Gender: " + gender + ", people: " + p));

    // anyMatch
    System.out.println();
    System.out.println("Check if at least one person is older than 80 years");
    boolean havePeopleWithAgeGreaterThan80 = people
        .stream()
        .anyMatch(person -> person.getAge() >= 80);
    System.out.println("Have people with age greater than 80: " + havePeopleWithAgeGreaterThan80);
  }
}


class Person {

  public enum Gender {
    FEMALE,
    MALE
  }

  private final String name;
  private int age;
  private final Gender gender;

  Person(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Gender getGender() {
    return gender;
  }

  public void setAge(int newAge) {
    if (newAge < 0) {
      throw new IllegalArgumentException("The number should be greater than zero!");
    }

    this.age = newAge;
  }

  @Override
  public String toString() {
    return "{ name: " + name + ", age: " + age + ", gender: " + gender + " }";
  }
}
