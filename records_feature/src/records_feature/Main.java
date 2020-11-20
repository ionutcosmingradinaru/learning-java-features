package records_feature;

public class Main {

  public static void main(String[] args) {
    var person = new Person("John", "Garden",26);
    System.out.println(person);

    System.out.println("Is weekend: " + isWeekend(Weekday.FRIDAY));
    System.out.println("Is weekend: " + isWeekend(Weekday.SUNDAY));
  }

  private static boolean isWeekend(Weekday day) {
    return switch (day) {
      case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> false;
      case SATURDAY, SUNDAY -> true;
    };
  }
}

record Person(
    String firstName,
    String lastName,
    int age) {
}

enum Weekday {
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
  SUNDAY
}