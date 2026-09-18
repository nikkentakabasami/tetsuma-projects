package ru.tet.demos;

@FunctionalInterface
public interface DemoSupplier<T> {
  T get() throws Exception;
}