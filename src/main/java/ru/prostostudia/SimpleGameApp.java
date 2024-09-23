package ru.prostostudia;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;

public class SimpleGameApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings gameSettings) {

    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new SimpleFactory());
        FXGL.spawn("javaStudent", 100,100);

    }
    public static void main(String[] args) {

        launch(args);

    }
}
