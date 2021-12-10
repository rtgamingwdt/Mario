package me.alpha.engine;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import me.alpha.Mario;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

  private Integer WIDTH, HEIGHT;
  private String TITLE;
  private Long glfwWindow;

  private static Window WINDOW = null;

  private Window() {
    this.WIDTH = 1920;
    this.HEIGHT = 1080;
    this.TITLE = "Mario";
  }

  public static Window get() {
    if(Window.WINDOW == null) {
      Window.WINDOW = new Window();
    }

    return Window.WINDOW;
  }

  public void run() {
    System.out.println("Hello LWJGL " + Version.getVersion() + "!");
    init();
    loop();
  }

  public void init() {
    GLFWErrorCallback.createPrint(System.err).set();

    if(!glfwInit()) {
      throw new IllegalStateException("Failed to create the GLFW window");
    }

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
    glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

    glfwWindow = glfwCreateWindow(this.WIDTH, this.HEIGHT, this.TITLE, NULL, NULL);

    if(glfwWindow == NULL) {
      throw new IllegalStateException("Failed to create the GLFW window");
    }

    glfwMakeContextCurrent(glfwWindow);

    glfwSwapInterval(1);

    glfwShowWindow(glfwWindow);

    GL.createCapabilities();
  }

  public void loop() {
    while(glfwWindowShouldClose(glfwWindow)) {
      glfwPollEvents();

      glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
      glClear(GL_COLOR_BUFFER_BIT);

      glfwSwapBuffers(glfwWindow);
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