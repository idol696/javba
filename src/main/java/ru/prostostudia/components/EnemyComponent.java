package ru.prostostudia.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.dsl.FXGL;

import static ru.prostostudia.SimpleGameApp.TILE_SIZE;

public class EnemyComponent extends Component {

    private AStarMoveComponent astarMoveComponent;
    private Entity player;
    private double speed;

    @Override
    public void onAdded() {
        player = FXGL.getGameWorld().getSingleton(entity -> entity.hasComponent(PlayerComponent.class));
        astarMoveComponent = entity.getComponent(AStarMoveComponent.class);
    }

    @Override
    public void onUpdate(double tpf) {
        speed += tpf;
        if (speed > 2 ) {
            if (player != null) {
                // Получаем текущие ячейки игрока
                int playerCellX = (int) (player.getX() / TILE_SIZE);
                int playerCellY = (int) (player.getY() / TILE_SIZE);

                // Перемещаем врага к ячейке игрока
                astarMoveComponent.moveToCell(playerCellX, playerCellY);
            }
            speed = 0;
        }
    }
}