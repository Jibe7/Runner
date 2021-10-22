import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {

    Camera cam;
    
    public GameScene(Parent root, double xpos, double ypos) {
        super(root,xpos,ypos);
        cam = new Camera(xpos, ypos);
    }

    public Camera getCam() {
        return cam;
    }
    public void setupCam(double x, double y) {
        cam.setX(x);
        cam.setY(y);
    }
}
