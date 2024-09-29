import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.SpawnData;
import org.junit.jupiter.api.Test;
import ru.prostostudia.SimpleFactory;
import ru.prostostudia.SimpleGameApp;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static com.almasb.fxgl.dsl.FXGL.*;
import static org.mockito.Mockito.*;


// Test class for SimpleGameApp
public class SimpleGameAppTest  {

    @Test
    public void testInitialization() {
        SimpleGameApp app = new SimpleGameApp();
        assertNotNull(app, "The game application should initialize properly");
    }

    @Test
    public void testPlayerSpawn() {

        GameWorld mockWorld = Mockito.mock(GameWorld.class);

        SpawnData data = new SpawnData(0, 0);

        Entity mockPlayer = new Entity();
        when(mockWorld.spawn("Player", data)).thenReturn(mockPlayer);

        mockWorld.spawn("Player", data);

        verify(mockWorld, times(1)).spawn("Player", data);
    }
}
