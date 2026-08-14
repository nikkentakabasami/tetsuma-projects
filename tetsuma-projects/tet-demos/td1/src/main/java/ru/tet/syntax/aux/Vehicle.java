package ru.tet.syntax.aux;



/**
 * sealed, permits
 * Позволяет определить, каким классам разрешено имплементировать класс
 * 
 * non-sealed
 * подкласс может быть объявлен как non-sealed, чтобы разрешить дальнейшее свободное наследование.
 * 
 * 
 */
public abstract sealed class Vehicle permits Car, Truck {

  protected final String registrationNumber;

  public Vehicle(String registrationNumber) {
      this.registrationNumber = registrationNumber;
  }

  public String getRegistrationNumber() {
      return registrationNumber;
  }

}
