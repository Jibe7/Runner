import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Runner");
        Group root = new Group();
        Scene theScene = new Scene(root, 600, 400);
        primaryStage.setScene(theScene);
        primaryStage.show();

        MovingThing hero=new MovingThing(0,0,"file:heros.png",274,14,55,84 );
        MovingThing desert=new MovingThing(0,0,"file:desert.png",0,0,600,400);
       // hero.setX(200);
        //hero.setY(200);

        root.getChildren().add(desert.getSprite());
        root.getChildren().add(hero.getSprite());

        //Image img = new Image("file:heros.png");
       // ImageView  iv = new ImageView(img);
        //iv.setX(200);
        //iv.setY(200);

        //double xp=50;
        //double yp=50;
        //GameScene gs = new GameScene(theScene,xp,yp);


        //Rectangle2D view = new Rectangle2D(0,0,80,100);
       // iv.setViewport(view);

       // root.getChildren().add(iv);

        //primaryStage.setTitle("mon appli");
      //  primaryStage.setScene(theScene);
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
