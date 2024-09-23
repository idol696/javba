package ru.prostostudia;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;

public class SimpleFactory implements EntityFactory {
    @Spawns("javaStudent")
    public Entity javaStudent(SpawnData data) {
        return FXGL.entityBuilder(data)
                .view(new Circle(40,40, 40, Color.GREEN))
                .build();
    }
}
