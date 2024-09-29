package ru.prostostudia;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;


import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import ru.prostostudia.components.EnemyComponent;
import ru.prostostudia.components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ru.prostostudia.SimpleGameApp.TILE_SIZE;
import static ru.prostostudia.SimpleType.*;

public class SimpleFactory implements EntityFactory {


    @Spawns("w")
    public Entity newWall(SpawnData data) {
        return entityBuilder(data)
                .type(WALL)
                .viewWithBBox(new Rectangle(40, 40, Color.GRAY.saturate()))
                .build();
    }

    @Spawns("b")
    public Entity newBrick(SpawnData data) {
        return entityBuilder(data)
                .type(BRICK)
                .viewWithBBox(texture("brick.png",40,40))
                .build();
    }

    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder(data)
                .atAnchored(new Point2D(20, 20), new Point2D(20, 20))
                .type(PLAYER)
                .viewWithBBox(new Rectangle(TILE_SIZE, TILE_SIZE, Color.BLUE))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(40, 40, 150))
                .with(new AStarMoveComponent(FXGL.<SimpleGameApp>getAppCast().getGrid()))
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("Enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder(data)
                .type(ENEMY)
                .atAnchored(new Point2D(20, 20), new Point2D(20, 20))
                .at(10 * TILE_SIZE,10 * TILE_SIZE)
                .viewWithBBox(new Rectangle(TILE_SIZE, TILE_SIZE, Color.RED))
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(40, 40, 80))
                .with(new AStarMoveComponent(FXGL.<SimpleGameApp>getAppCast().getGrid()))
                .with(new EnemyComponent())
                .build();
    }
}
