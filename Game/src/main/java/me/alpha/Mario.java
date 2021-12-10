package me.alpha;

import me.alpha.engine.Window;

/**
 * Hello world!
 *
 */
public class Mario {

  private static Mario MARIO;

  public static void main(String[] args) {
    Window window = Window.get();
    window.run();
  }

  public static Mario get() {
    return MARIO;
  }
}