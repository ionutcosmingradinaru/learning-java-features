package functional_interfaces;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    print(() -> System.out.println("This is a simple example of lambda expression"));

    var names = List.of("John", "Sofia", "Tatiana", "Anastasia");
    print(names, s -> s.startsWith("T"));
  }

  private static void print(MyPrintable moveable) {
    moveable.print();
    moveable.defaultMessage();
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
