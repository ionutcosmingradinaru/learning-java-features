package generics_feature;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    // A generic class for a single object
    MyFirstGeneric<Integer> integerObj = new MyFirstGeneric<>(11);
    System.out.println(integerObj);

    MyFirstGeneric<String> stringObj = new MyFirstGeneric<>("This is a message.");
    System.out.println(stringObj);

    // A generic class as a map, but the key needs to be a String, and the value should be a Number
    MyMapClass<String, Integer> myMap = new MyMapClass<>();
    myMap.add("first", 1);
    myMap.add("second", 2);
    myMap.add("third", 3);
    myMap.add("first", 4);
    myMap.print();
  }
}

class MyFirstGeneric<T> {

  private T obj;

  MyFirstGeneric(T obj) {
    this.obj = obj;
  }

  @Override
  public String toString() {
    return "The class is " + obj.getClass().getSimpleName() + " and value is "  + obj;
  }
}

class MyMapClass<K extends String, V extends Number> {

  private Map<K, V> map = new HashMap<>();

  public void add(K key, V value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException();
    }

    map.put(key, value);
  }

  public void print() {
    map.forEach((k, v) -> System.out.println("{ key: " + k + ", value: " + v + " }"));
  }
}
