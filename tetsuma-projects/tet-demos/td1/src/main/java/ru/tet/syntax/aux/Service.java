package ru.tet.syntax.aux;


/**
 * sealed, permits
 * Позволяет определить, каким классам разрешено имплементировать интерфейс
 * 
 */
public sealed interface Service permits Car, Truck {

  int getMaxServiceIntervalInMonths();

  default int getMaxDistanceBetweenServicesInKilometers() {
      return 100000;
  }

}
