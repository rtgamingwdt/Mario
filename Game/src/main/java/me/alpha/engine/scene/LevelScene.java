package me.alpha.engine.scene;

import me.alpha.engine.Window;

public class LevelScene extends Scene {

  public LevelScene() {
    System.out.println("Inside Level Scene!");
    Window.get().R = 1f;
    Window.get().G = 1f;
    Window.get().B = 1f;
  }

  @Override
  public void update(float dt) {
    // TODO Auto-generated method stub
  }
}