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
    private Foe heart1;
    private Foe heart2;
    private int bg1Length;
    private int bg1Width;
    private int bg2Length;
    private int bg2Width;
    private int bgRxpos;
    private int bgRypos;
    private int bgLxpos;
    private int bgLypos;
    private Camera cam;
    private staticThing bgR;
    private staticThing bgL;
    private Hero hero;
    private Foe foe1;
    private Foe foe2;
    private long prevTime=0;
    private static long periodFrames=300; //nbre de nanosecondes pour faire 1/60 secondes
    private int invincible=0;
    private double invicibilityTime=2000000000.0;
    private int numberOfLives=4;
    private boolean invicible=false;
    private boolean contact=false;
    private int gameState=0; // 0 'Click to start', 1 game is played, 2 end screen 'Click to restart'
    private ImageView endSprite;



    AnimationTimer timer = new AnimationTimer() {
        public void handle(long time) {
            if(time - prevTime > 1e9/120) {
                //System.out.println((time-prevTime)*1e-9); // si on a du 0.016 ça veut dire qu'on a 60fps

                hero.update(time,(int) cam.getX());
                cam.update(time,hero);

                if (invicibilityTime<0) { //25000000000.0
                    invicibilityTime=2000000000.0;
                    invicible=false;
                }
                if (contact==false) {
                    int i=0;
                    while (invicible==false&&i<foes.size()) {
                        invicible = hero.getHitBox2(foes.get(i).hitbox); //il y a contact il est invincible
                        i++;
                        if (invicible==true) { // s'il y a eu un contact on lui retire une vie
                            numberOfLives -= 1;
                            contact=true;
                        }  // le héro a été touché !
                    }
                }
                contact=false;

                if (invicible==true) {
                    invicibilityTime=invicibilityTime-(time-prevTime);
                }

                prevTime = time;
                update(time);
            }
        } };


    public GameScene(Parent root, double xcam, double ycam, double x, double y,double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width,int maxI,int[] Lh) { //(x,y) taille de l'image, (xcam,ycam) position de la caméra
        super(root,x,y);
        cam = new Camera(xcam, ycam);
        foe1 = new Foe(600, 230,"file:foe.png",144,19,72,104 );
        foe2 = new Foe(1300, 230,"file:foe.png",144,19,72,104 );
        foe1.getSprite().setFitHeight(62);
        foe1.getSprite().setFitWidth(94);
        foe1.getSprite().setPreserveRatio(true);
        foe2.getSprite().setFitHeight(62);
        foe2.getSprite().setFitWidth(94);
        foe2.getSprite().setPreserveRatio(true);
        heart1 = new Foe(202,230,"file:hearts.png",48,29,222,220);
        heart2 = new Foe(452,230,"file:hearts.png",48,29,222,220);
        endSprite = new ImageView(new Image("file:gameover.jpg"));
        endSprite.setViewport(new Rectangle2D(79,64,1197,673));
        endSprite.setX(-1000);
        endSprite.setY(-1000);
        endSprite.setFitHeight(350);
        endSprite.setFitWidth(780);
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
        numberOfLives=4;
        timer.start();
    }

    public void contacts(long time, long prevTime, Hero hero) {

        if (invicibilityTime<0) { //25000000000.0
            invicibilityTime=2000000000.0;

            //hero.setIsInvicible(0);
            //invincible=0;
        }

        if (invicible==false) {
            int i=0;
            while (invicible==false&&i<foes.size()) {
                invicible = hero.getHitBox2(foes.get(i).hitbox); //il y a contact il est invincible
                i++;
                if (invicible==true) { // s'il y a eu un contact on lui retire une vie
                    numberOfLives -= 1;
                }  // le héro a été touché !
            }
        }

        if (invicible) {
            invicibilityTime=invicibilityTime-(time-prevTime);
        }

    }

    public void update(long l) {
        bgL.getSprite().setX(bgLxpos-(cam.getX()%800));
        bgL.getSprite().setY(bgLypos-cam.getY());
        bgR.getSprite().setX(bgRxpos-(cam.getX()%800));
        bgR.getSprite().setY(bgRypos-cam.getY());
        //if (numberOfLives<0) {numberOfLives=4;}
        if (numberOfLives>=4) {
            heart1.getSprite().setViewport(new Rectangle2D(48,29,222,220));
            heart2.getSprite().setViewport(new Rectangle2D(48,29,222,220));
        }
        else if (numberOfLives==3) {
            heart1.getSprite().setViewport(new Rectangle2D(48,29,222,220));
            heart2.getSprite().setViewport(new Rectangle2D(308,42,222,220));
        }
        else if (numberOfLives==2) {
            heart1.getSprite().setViewport(new Rectangle2D(48,29,222,220));
            heart2.getSprite().setViewport(new Rectangle2D(569,40,222,220));
        }
        else if (numberOfLives==1) {
            heart1.getSprite().setViewport(new Rectangle2D(308,42,222,220));
            heart2.getSprite().setViewport(new Rectangle2D(569,40,222,220));
        }
        else if (numberOfLives<=0) {
            heart1.getSprite().setViewport(new Rectangle2D(569,40,222,220));
            heart2.getSprite().setViewport(new Rectangle2D(569,40,222,220));
            gameState=2;



        }
        heart1.getSprite().setFitHeight(30);
        heart1.getSprite().setFitWidth(30);
        heart1.getSprite().setPreserveRatio(true);
        heart1.getSprite().setX(hero.getX()-cam.getX());
        heart1.getSprite().setY(hero.getY()-cam.getY()-40);
        heart2.getSprite().setFitHeight(30);
        heart2.getSprite().setFitWidth(30);
        heart2.getSprite().setPreserveRatio(true);
        heart2.getSprite().setX(hero.getX()-cam.getX()+33);
        heart2.getSprite().setY(hero.getY()-cam.getY()-40);


        for (int i=0;i<foes.size();i++) {
            double foepos=foes.get(i).getXfoe();
            if (foepos<(cam.getX()- foes.get(i).getLength()-200)) {
                foepos=foepos+1400+Math.random()*250;
                foes.get(i).setXfoe(foepos);

                }
            foes.get(i).setHitbox(new Rectangle2D(foes.get(i).getXfoe(),foes.get(i).getYfoe(),72-40,104-74)); //length and width of foe 72 and 104
            }
        for (int i=0;i<foes.size();i++) {
            foes.get(i).getSprite().setX(foes.get(i).getXfoe()-cam.getX()); //foe.getSprite().setX(foe.getXfoe()-cam.getX()%1300);
            foes.get(i).getSprite().setY(foes.get(i).getYfoe()-cam.getY()); }
        hero.getSprite().setX(hero.getX()-cam.getX());
        hero.getSprite().setY(hero.getY()-cam.getY());
        this.setbgL(0,0,bg2Length,this.bg2Width);
        this.setbgR(0,0,(int)cam.getX()%800,this.bg1Width);
        this.setOnMouseClicked( (event)->{
            hero.jump();
            if (hero.getCompt()==77) {
                endSprite.setX(0);
                endSprite.setY(0);
                hero.setCompt(99);
            }
        });

        if (gameState==2) {
            hero.setA_x(0);
            hero.setA_y(0);
            hero.setGravity(0);
            hero.setAx_prec(0);
            hero.setAy_prec(0);
            hero.setV_x(0);
            hero.setV_y(0);
            hero.setVx_prec(0);
            hero.setVy_prec(0);
            hero.setCompt(77); //le héro ne court pas sur place grâce à cette ligne


            //endSprite.setX(0);
            //endSprite.setY(0);
            }

    }

    public Foe getHeart1() {
        return heart1;
    }

    public int getGameState() {
        return gameState;
    }

    public Foe getHeart2() {
        return heart2;
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

    public ImageView getEndSprite() {
        return endSprite;
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
