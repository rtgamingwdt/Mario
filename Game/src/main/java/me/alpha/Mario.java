package me.alpha;

import me.alpha.engine.Window;

/**
 * Hello world!
 *
 */
public class Mario {

  private static Mario instance;

  public static void main(String[] args) {
    Window WINDOW = Window.get();
    WINDOW.run();
  }

  public static Mario get() {
    return instance;
  }
}