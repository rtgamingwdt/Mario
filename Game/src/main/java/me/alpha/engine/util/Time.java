package me.alpha.engine.util;

public class Time {

  public static float TIME_STARTED = System.nanoTime();

  public static float getTime() {
    return Float.valueOf(String.valueOf((System.nanoTime() - TIME_STARTED) * 1E-9));
  }
}