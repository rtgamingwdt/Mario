package me.alpha.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;

import me.alpha.engine.event.*;
import me.alpha.engine.scene.LevelEditorScene;
import me.alpha.engine.scene.LevelScene;
import me.alpha.engine.scene.Scene;
import me.alpha.engine.util.Time;

public class Window {

  private Integer WIDTH, HEIGHT;
  private String TITLE;
  private Long GLFW_WINDOW;
  public Float R, G, B, A;
  private Boolean FADE_TO_BLACK = false;

  private static Window instance = null;

  private static Scene CURRENT_SCENE;

  private Window() {
    this.WIDTH = 1920;
    this.HEIGHT = 1080;
    this.TITLE = "Mario";
    R = 1f;
    G = 1f;
    B = 1f;
    A = 1f;
  }

  public static void changeScene(Integer newScene) {
    switch(newScene) {
      case 0:
        CURRENT_SCENE = new LevelEditorScene();
        //CURRENT_SCENE.init();
        break;
      case 1:
        CURRENT_SCENE = new LevelScene();
        break;
      default:
        assert false : "Unknown Scene '" + newScene + "'";
        break;
    }
  }

  public static Window get() {
    if (Window.instance == null) {
      Window.instance = new Window();
    }

    return Window.instance;
  }

  public void run() {
    System.out.println("Hello LWJGL " + Version.getVersion() + "!");

    init();
    loop();

    glfwFreeCallbacks(GLFW_WINDOW);
    glfwDestroyWindow(GLFW_WINDOW);
    glfwTerminate();
    glfwSetErrorCallback(null).free();
  }

  public void init() {
    GLFWErrorCallback.createPrint(System.err).set();

    if (!glfwInit()) {
      throw new IllegalStateException("Failed to create the GLFW window");
    }

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
    glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

    GLFW_WINDOW = glfwCreateWindow(this.WIDTH, this.HEIGHT, this.TITLE, NULL, NULL);

    glfwSetCursorPosCallback(GLFW_WINDOW, MouseListener::mousePosCallback);
    glfwSetMouseButtonCallback(GLFW_WINDOW, MouseListener::mouseButtonCallback);
    glfwSetScrollCallback(GLFW_WINDOW, MouseListener::mouseScrollCallback);
    glfwSetKeyCallback(GLFW_WINDOW, KeyListener::keyCallback);

    if (GLFW_WINDOW == NULL) {
      throw new IllegalStateException("Failed to create the GLFW window");
    }

    glfwMakeContextCurrent(GLFW_WINDOW);

    glfwSwapInterval(1);

    glfwShowWindow(GLFW_WINDOW);

    GL.createCapabilities();

    Window.changeScene(0);
  }

  public void loop() {
    Float BEGIN_TIME = Time.getTime();
    Float END_TIME;
    Float DT = -1.0f;

    while (glfwWindowShouldClose(GLFW_WINDOW)) {
      glfwPollEvents();

      glClearColor(R, G, B, A);
      glClear(GL_COLOR_BUFFER_BIT);

      if(DT >= 0) {
        CURRENT_SCENE.update(DT);
      }

      glfwSwapBuffers(GLFW_WINDOW);

      END_TIME = Time.getTime();
      DT = END_TIME - BEGIN_TIME;
      BEGIN_TIME = END_TIME;
    }
  }

  public Integer getWidth() {
    return WIDTH;
  }

  public void setWidth(Integer width) {
    this.WIDTH = width;
  }

  public Integer getHeight() {
    return HEIGHT;
  }

  public void setHeight(Integer height) {
    this.HEIGHT = height;
  }

  public String getTitle() {
    return TITLE;
  }

  public void setTitle(String title) {
    this.TITLE = title;
  }
}