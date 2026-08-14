package ru.tet.syntax.aux;


public final class Truck extends Vehicle implements Service {

  private final int loadCapacity;

  public Truck(int loadCapacity, String registrationNumber) {
      super(registrationNumber);
      this.loadCapacity = loadCapacity;
  }

  public int getLoadCapacity() {
      return loadCapacity;
  }

  @Override
  public int getMaxServiceIntervalInMonths() {
      return 18;
  }

}
