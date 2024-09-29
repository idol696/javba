package ru.prostostudia;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.entity.level.text.TextLevelLoader;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import javafx.scene.input.KeyCode;
import ru.prostostudia.components.PlayerComponent;

import static com.almasb.fxgl.dsl.FXGL.*;
import static ru.prostostudia.SimpleType.BRICK;
import static ru.prostostudia.SimpleType.WALL;

public class SimpleGameApp extends GameApplication {

    public static final int TILE_SIZE = 40;

    private AStarGrid grid;

    private PlayerComponent playerComponent;

    public AStarGrid getGrid() {
        return grid;
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("Simple App");
        gameSettings.setVersion("0.1");
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveUp();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveLeft();
            }
        }, KeyCode.A);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveDown();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                playerComponent.moveRight();
            }
        }, KeyCode.D);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new SimpleFactory());
        Level level = getAssetLoader().loadLevel("0.txt", new TextLevelLoader(40, 40, '0'));
        getGameWorld().setLevel(level);

        grid = AStarGrid.fromWorld(getGameWorld(), 15, 15, 40, 40, type -> {
            if (type.equals(WALL) || type.equals(BRICK))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });

        Entity player = spawn("Player");
        Entity enemy = spawn("Enemy");

        playerComponent = player.getComponent(PlayerComponent.class);

    }
    public static void main(String[] args) {

        launch(args);

    }
}
