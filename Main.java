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
        double x = 0;
        double y = 0;
        double xhpos = 50; //xhpos, yhpos position initiale du héro
        double yhpos = 250;
        int[] Lh = {22,14,53,84,96,3,65,95,174,16,76,82,274,14,55,84,350,5,61,93,428,18,72,80}; //liste des découpages des images de heros.png
        //GameScene gs = new GameScene(root, 7600,0, 1600,400,xhpos-gs.getCam().getX(),yhpos-gs.getCam().getY(),"file:heros.png",274,14,55,84);
        GameScene gs = new GameScene(root, 0,0, 800,400,xhpos,yhpos,"file:heros.png",22,14,55,84,6,Lh);
        gs.setupCam(x,y);
        //gs.setbgR();
        //gs.timer.start();
        primaryStage.setScene(gs);
        primaryStage.show();

        //MovingThing hero=new MovingThing(xhpos-gs.getCam().getX(),yhpos-gs.getCam().getY(),"file:heros.png",274,14,55,84 ); ICI

        //staticThing desert1=new staticThing(0,0,"file:desert1.png",800,0,800,400);
        //staticThing desert2=new staticThing(800,0,"file:desert1.png",800,0,800,400);
        //staticThing desert3=new staticThing(1600,0,"file:desert2.png",0,0,800,400);

       // hero.setX(200);
        //hero.setY(200);
        //desert1.getSprite().setViewport(new Rectangle2D(0,0,400,600));

        root.getChildren().add(gs.getbgR().getSprite());
        root.getChildren().add(gs.getbgL().getSprite());

        root.getChildren().add(gs.getHero().getSprite());

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
