package functional_interfaces;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Main {

  public static void main(String[] args) {
    print(() -> System.out.println("This is a simple example of lambda expression"));

    var names = List.of("John", "Sofia", "Tatiana", "Anastasia");
    print(names, s -> s.startsWith("T"));

    UnaryOperator<String> uppercaseByFunction = s -> s.toUpperCase();
    var resultUpperFunction = uppercaseByFunction.apply("john");
    System.out.println(resultUpperFunction);

    BinaryOperator<String> concatFunction = (s1, s2) -> s1 + s2;
    var resultConcatFunction = concatFunction.apply("John", "Tatiana");
    System.out.println(resultConcatFunction);

    var person = new Person("John","john@icloud.com");
    var resultValidation = PersonValidator
        .isNameValid()
        .and(PersonValidator.isEmailValid())
        .apply(person);
    System.out.println(resultValidation);
  }

  private static void print(MyPrintable movable) {
    movable.print();
    movable.defaultMessage();
  }

  private static void print(List<String> names, MyFilter<String> filter) {
    for (String name : names) {
      if (filter.test(name)) {
        System.out.println(name);
      }
    }
  }
}

@FunctionalInterface
interface MyPrintable {

  void print();

  default void defaultMessage() {
    System.out.println("This is a default message!");
  }
}

@FunctionalInterface
interface MyFilter<T> {

  boolean test(T obj);
}

// Combinator Patter
record Person(String name, String email) {
}

enum ValidationResult {
  SUCCESS,
  NAME_NOT_VALID,
  EMAIL_NOT_VALID
}

interface PersonValidator extends Function<Person, ValidationResult> {

  static PersonValidator isNameValid() {
    return p -> (p.name() != null && !p.name().strip().isEmpty())
        ? ValidationResult.SUCCESS : ValidationResult.NAME_NOT_VALID;
  }

  static PersonValidator isEmailValid() {
    return p -> (p.email() != null && p.email().contains("@"))
        ? ValidationResult.SUCCESS : ValidationResult.EMAIL_NOT_VALID;
  }

  default PersonValidator and(PersonValidator other) {
    return p -> {
      var result = this.apply(p);
      return result.equals(ValidationResult.SUCCESS) ? other.apply(p) : result;
    };
  }
}
