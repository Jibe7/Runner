import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameScene extends Scene {
    private static double upShiftBg = 50;
    private static double downShiftBg = 10;
    Parent rot;
    private int bg1Length;
    private int bg1Width;
    private int bg2Length;
    private int bg2Width;
    private int bgRxpos;
    private int bgRypos;
    private int bgLxpos;
    private int bgLypos;
    private Camera cam;
    private int numberOfLives;
    private staticThing bgR;
    private staticThing bgL;
    private Hero hero;
    private long prevTime=0;
    private static long periodFrames=300; //nbre de nanosecondes pour faire 1/60 secondes
    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            if(time - prevTime > 1e9/120) {
                //System.out.println((time-prevTime)*1e-9); // si on a du 0.016 ça veut dire qu'on a 60fps

                hero.update(time,(int) cam.getX());
                cam.update(time,hero);
                update(time);

                prevTime = time;

            }

        } };

    public GameScene(Parent root, double xcam, double ycam, double x, double y,double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width,int maxI,int[] Lh) { //(x,y) taille de l'image, (xcam,ycam) position de la caméra
        super(root,x,y);
        rot=root;
        cam = new Camera(xcam, ycam);
        bg1Length=800;
        bg1Width=400;
        bg2Length=800;
        bg2Width=400;
        bgLxpos=0;
        bgLypos=(int)-GameScene.getUpShiftBg();
        bgRxpos=800;
        bgRypos=(int)-GameScene.getUpShiftBg();
        bgL=new staticThing(bgLxpos-(xcam%800),bgLypos,"file:desert1.png",0,0,bg2Length,bg2Width);
        bgR=new staticThing(bgRxpos-(xcam%800),bgRypos,"file:desert1.png",0,0,bg1Length,bg1Width);
        hero= new Hero(xpos,ypos,filename,x1,y1,length,width,maxI,Lh);
        numberOfLives=3;
        timer.start();
    }

    public void update(long l) {
        bgL.getSprite().setX(bgLxpos-(cam.getX()%800));
        bgL.getSprite().setY(bgLypos-cam.getY());
        bgR.getSprite().setX(bgRxpos-(cam.getX()%800));
        bgR.getSprite().setY(bgRypos-cam.getY());
        //this.setbgL(0,0,this.bg2Length,this.bg2Width);
        //this.setbgR(0,0,this.bg1Length,this.bg1Width);
        this.setbgL(0,0,bg2Length,this.bg2Width);
        this.setbgR(0,0,(int)cam.getX()%800,this.bg1Width);
        this.setOnMouseClicked( (event)->{
            System.out.println("Jump");
            hero.jump(); });
    }

    public Hero getHero() {
        return hero;
    }

    public int getBg1Length() {
        return bg1Length;
    }

    public int getBg1Width() {
        return bg1Width;
    }

    public int getBg2Length() {
        return bg2Length;
    }

    public int getBg2Width() {
        return bg2Width;
    }

    public int getBg1xpos() {
        return bgRxpos;
    }

    public int getBg1ypos() {
        return bgRypos;
    }

    public int getBg2xpos() {
        return bgLxpos;
    }

    public int getBg2ypos() {
        return bgLypos;
    }

    public Camera getCam() {
        return cam;
    }

    public void setupCam(double x, double y) {
        cam.setX(x);
        cam.setY(y);
    }

    public staticThing getbgR() {
        return bgR;
    }

    public staticThing getbgL() {
        return bgL;
    }

    public void setbgR(int x1,int y1,int length, int width) {
        bgR.modSprite(x1,y1,length,width);
    }

    public void setbgL(int x1,int y1,int length, int width) {
        bgL.modSprite(x1,y1,length,width);
    }

    public void update() {
        bgR.getSprite().setViewport(new Rectangle2D(this.cam.getX(),this.cam.getY(),bg1Length-this.cam.getX()%bg1Length,bg1Width));
        bgL.getSprite().setViewport(new Rectangle2D((double) bg1Length,this.cam.getY(),bg1Length+((int) this.cam.getX()%bg2Length),bg2Width));

    }

    public static double getUpShiftBg() {
        return upShiftBg;
    }

    public static double getDownShiftBg() {
        return downShiftBg;
    }


}
