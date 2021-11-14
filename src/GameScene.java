import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameScene extends Scene {
    private static double upShiftBg = 50; //on affiche le background 'coupé' de façon à ce que lorsqu'on bouge la caméra on ne voit pas le blanc qu'il y a en haut de la fenêtre
    private static double downShiftBg = 10; // même idée mais pour ne pas voir le blanc qu'il y a en bas de la fenêtre
    private ArrayList<Foe> foes = new ArrayList<Foe>();
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
    private Foe foe1;
    private Foe foe2;
    private long prevTime=0;
    private static long periodFrames=300; //nbre de nanosecondes pour faire 1/60 secondes
    private int invincible=0;
    private double invicibilityTime=2000000000.0;




    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            if(time - prevTime > 1e9/120) {
                //System.out.println((time-prevTime)*1e-9); // si on a du 0.016 ça veut dire qu'on a 60fps


                hero.update(time,(int) cam.getX());
                cam.update(time,hero);
                System.out.println(invicibilityTime);
                System.out.println(hero.getIsInvicible());
                if (invicibilityTime<0) { //25000000000.0
                    System.out.println("Il est vulnérable");
                    invicibilityTime=2000000000.0;

                    hero.setIsInvicible(0);
                    invincible=0;
                }
                for (int i=0;i<foes.size();i++) {
                    invincible=hero.getHitBox(foes.get(i));
                    if (invincible==1) {hero.setIsInvicible(1); i++;}
                }
                if (hero.getIsInvicible()==1) {
                    invicibilityTime=invicibilityTime-(time-prevTime);
                }
                //foe.update(time,(int) cam.getX());
                prevTime = time;
                update(time);



            }

        } };

    public GameScene(Parent root, double xcam, double ycam, double x, double y,double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width,int maxI,int[] Lh) { //(x,y) taille de l'image, (xcam,ycam) position de la caméra
        super(root,x,y);
        cam = new Camera(xcam, ycam);
        foe1 = new Foe(1200, 200,"file:foe.png",144,19,72,104 );
        foe2 = new Foe(1700, 200,"file:foe.png",144,19,72,104 );
        foes.add(foe1);
        foes.add(foe2);
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
        for (int i=0;i<foes.size();i++) {
            foes.get(i).getSprite().setX(foes.get(i).getXfoe()-cam.getX()); //foe.getSprite().setX(foe.getXfoe()-cam.getX()%1300);
            foes.get(i).getSprite().setY(foes.get(i).getYfoe()-cam.getY()); }
        for (int i=0;i<foes.size();i++) {
            if (foes.get(i).getXfoe()<cam.getX()- foes.get(i).getLength()) {
                foes.get(i).setXfoe(foes.get(i).getXfoe()+1000+Math.random()*(1500-200));
                }
            }
        hero.getSprite().setX(hero.getX()-cam.getX());
        hero.getSprite().setY(hero.getY()-cam.getY());
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

    public Foe getFoe1() {
        return foe1;
    }

    public ArrayList<Foe> getFoes() {
        return foes;
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
