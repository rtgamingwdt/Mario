package me.alpha.engine.scene;

import me.alpha.engine.Window;
import me.alpha.engine.event.KeyListener;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

  private Boolean CHANGING_SCENE = false;
  private Float TIME_TO_CHANGE_SCENE = 2.0f;

  public LevelEditorScene() {
    System.out.println("Inside Level Editor Scene!");
  }

  @Override
  public void update(float dt) {
    
    System.out.println("" + Math.round(1f / dt) + " FPS");

    if(!CHANGING_SCENE && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
      CHANGING_SCENE = true;
    }

    if(CHANGING_SCENE && TIME_TO_CHANGE_SCENE > 0) {
      TIME_TO_CHANGE_SCENE -= dt;
      Window.get().R -= dt * 5.0f;
      Window.get().G -= dt * 5.0f;
      Window.get().B -= dt * 5.0f;
    } else if(CHANGING_SCENE) {
      Window.changeScene(1);
    }
  }
}