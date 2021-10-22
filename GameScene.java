import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {

    Camera cam;
    
    public GameScene(Parent parent, double xpos, double ypos) {
        super(parent);
        cam = new Camera(xpos, ypos);
    }



}
