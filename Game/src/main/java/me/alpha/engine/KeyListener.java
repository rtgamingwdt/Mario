package me.alpha.engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {

  private static KeyListener instance;
  private Boolean KEY_PRESSED[] = new Boolean[350];

  private KeyListener() {

  }

  public static KeyListener get() {
    if(KeyListener.instance == null) {
      KeyListener.instance = new KeyListener();
    }

    return KeyListener.instance;
  }

  public static void keyCallback(Long window, Integer key, Integer scanCode, Integer action, Integer mods) {
    if(action == GLFW_PRESS) {
      get().KEY_PRESSED[key] = true;
    } else if(action == GLFW_RELEASE) {
      get().KEY_PRESSED[key] = false;
    }
  }

  public static Boolean isKeyPressed(Integer keyCode) {
    return get().KEY_PRESSED[keyCode];
  }
}