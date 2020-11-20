package sealed_feature;

public class Main {

  public static void main(String[] args) {
    Shape shape1 = new Circle();
    Shape shape2 = new Square();

    shape1.draw();
    shape2.draw();
  }
}

sealed abstract class Shape permits Circle, Square {

  abstract void draw();
}

final class Circle extends Shape {

  @Override
  void draw() {
    System.out.println("Draw a circle");
  }
}

final class Square extends Shape {

  @Override
  void draw() {
    System.out.println("Draw a square");
  }
}
