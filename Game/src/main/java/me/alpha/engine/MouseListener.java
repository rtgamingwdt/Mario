package me.alpha.engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {

  private static MouseListener instance;
  private Double SCROLL_X, SCROLL_Y;
  private Double X_POS, Y_POS, LAST_X, LAST_Y;
  private Boolean MOUSE_BUTTON_PRESSED[] = new Boolean[3];
  private Boolean IS_DRAGGING;

  public MouseListener() {
    this.SCROLL_X = 0.0;
    this.SCROLL_Y = 0.0;
    this.X_POS = 0.0;
    this.Y_POS = 0.0;
    this.LAST_X = 0.0;
    this.LAST_Y = 0.0;
  }

  public static MouseListener get() {
    if(MouseListener.instance == null) {
      MouseListener.instance = new MouseListener();
    }

    return MouseListener.instance;
  }

  public static void mousePosCallback(Long window, Double xPos, Double yPos) {
    get().LAST_X = get().X_POS;
    get().LAST_Y = get().Y_POS;
    get().X_POS = xPos;
    get().Y_POS = yPos;
    get().IS_DRAGGING = get().MOUSE_BUTTON_PRESSED[0] || get().MOUSE_BUTTON_PRESSED[1] || get().MOUSE_BUTTON_PRESSED[2];
  }

  public static void mouseButtonCallback(Long window, Integer button, Integer action, Integer mods) {
    if(action == GLFW_PRESS) {
      if(button < get().MOUSE_BUTTON_PRESSED.length) {
        get().MOUSE_BUTTON_PRESSED[button] = true;
      }
    } else if(action == GLFW_RELEASE) {
      if(button < get().MOUSE_BUTTON_PRESSED.length) {
        get().MOUSE_BUTTON_PRESSED[button] = false;
        get().IS_DRAGGING = false;
      }
    }
  }

  public static void mouseScrollCallback(Long window, Double xOffset, Double yOffset) {
    get().SCROLL_X = xOffset;
    get().SCROLL_Y = yOffset;
  }

  public static void endFrame() {
    get().SCROLL_X = 0.0;
    get().SCROLL_Y = 0.0;
    get().LAST_X = get().X_POS;
    get().LAST_Y = get().Y_POS;
  }

  public static Float getX() {
    return Float.valueOf(String.valueOf(get().X_POS));
  }

  public static Float getY() {
    return Float.valueOf(String.valueOf(get().Y_POS));
  }

  public static Float getDx() {
    return Float.valueOf(String.valueOf(get().LAST_X - get().X_POS));
  }

  public static Float getDy() {
    return Float.valueOf(String.valueOf(get().LAST_Y - get().Y_POS));
  }

  public static Float getScrollX() {
    return Float.valueOf(String.valueOf(get().SCROLL_X));
  }

  public static Float getScrollY() {
    return Float.valueOf(String.valueOf(get().SCROLL_Y));
  }

  public static Boolean isDragging() {
    return get().IS_DRAGGING;
  }

  public static Boolean mouseButtonDown(Integer button) {
    if(button < get().MOUSE_BUTTON_PRESSED.length) {
      return get().MOUSE_BUTTON_PRESSED[button];
    } else {
      return false;
    }
  }
}