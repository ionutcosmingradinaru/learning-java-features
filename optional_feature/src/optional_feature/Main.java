package optional_feature;

import java.util.List;
import java.util.Optional;

public class Main {

  public static void main(String[] args) {
    getByName("John")
        .ifPresent(System.out::println);

    var name = getByName("Mike")
        .orElse("Default");
    System.out.println(name);
  }

  private static Optional<String> getByName(String name) {
    var names = List.of("John", "Tatiana", "Andrei", "Sofia", "Anastasia");
    return names
        .stream()
        .filter(s -> s.equals(name))
        .findFirst();
  }
}
