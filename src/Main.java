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
        //Scene theScene = new Scene(root, 600, 400);
        //primaryStage.setScene(theScene);
        //primaryStage.show();
        GameScene gs = new GameScene(root, 2400,400);
        primaryStage.setScene(gs);
        primaryStage.show();

        MovingThing hero=new MovingThing(0,0,"file:heros.png",274,14,55,84 );
        staticThing desert1=new staticThing(0,0,"file:desert1.png",0,0,800,400);
        staticThing desert2=new staticThing(800,0,"file:desert1.png",0,0,800,400);
        staticThing desert3=new staticThing(1600,0,"file:desert2.png",0,0,800,400);
       // hero.setX(200);
        //hero.setY(200);

        root.getChildren().add(desert1.getSprite());
        root.getChildren().add(desert2.getSprite());
        root.getChildren().add(desert3.getSprite());
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
