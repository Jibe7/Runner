import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Runner");
        Group root = new Group();
        double x = 0; // initial position of the camera
        double y = 0;
        double xhpos = 50; //initial position of the hero
        double yhpos = 220;

        int[] Lh = {22,14,53,84,96,3,65,95,174,16,76,82,274,14,55,84,350,5,61,93,428,18,72,80}; //cut of the images of heros.png
        GameScene gs = new GameScene(root, 0,0, 780,400-GameScene.getDownShiftBg()-GameScene.getUpShiftBg(),xhpos,yhpos,"file:heros.png",22,14,55,84,6,Lh);
        gs.setupCam(x,y);
        gs.getTimer().start();
        primaryStage.setScene(gs);
        primaryStage.show();

        // ROOTS
        root.getChildren().add(gs.getbgR().getSprite());
        root.getChildren().add(gs.getbgL().getSprite());
        for (int j=0;j<gs.getFoes().size();j++) {
            root.getChildren().add(gs.getFoes().get(j).getSprite());
        }
        root.getChildren().add(gs.getHeart1().getSprite());
        root.getChildren().add(gs.getHeart2().getSprite());
        root.getChildren().add(gs.getHero().getSprite());

        root.getChildren().add(gs.getEndSprite());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
