import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Hero extends AnimatedThing {
    private int list[];
    private int compt;
    private double gravity=200;
    private double a_x=50;
    private double ax_prec=50;
    private double pos_x=160;
    private double x_prec=150;
    private double v_x=20;
    private double vx_prec=15;
    private final double positionY=200; //position de la route
    private double pos_y=positionY;
    private double y_prec=positionY;
    private double v_y=0;
    private double vy_prec=0;
    private double a_y=0;
    private double ay_prec=0;
    private double deltaT;
    private long pTime=0;
    private int isInvicible=0;
    private double length=55;
    //private double width=


    public Hero(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width, int maxI,int[] Lh) {
        super(xpos,ypos,filename,x1,y1,length,width);
        list = Lh;
        index=0;
        maxIndex=maxI;
        compt=0;
    }

    public void addList(int x1, int y1, int length, int width) {
        int[] tmp = new int[list.length+4];
        for (int i=0;i<list.length;i++) {
            tmp[i]=list[i];
        }
        tmp[list.length] = x1;
        tmp[list.length+1] = y1;
        tmp[list.length+2] = length;
        tmp[list.length+3] = width;
        list=tmp;
    }

    public void setList(int[] L) {
        list=L;
    }

    public void update(long time,int xcam) {
        //faire évoluer x et y puis :

        if (pTime==0) pTime=time;

        if (vy_prec!=0&&y_prec>=(positionY-0.001)) {vy_prec=0; a_y=0; ay_prec=0; v_y=0; y_prec=positionY;} //250
        deltaT = 1e-9*(time-pTime);
        a_x=ax_prec;
        a_y=ay_prec;

        if (v_x>600) {v_x=vx_prec; a_x=0; ax_prec=0;}
        else {v_x=vx_prec+deltaT*a_x;}
        //if (v_x>650) {ax_prec=0;} // arrête d'accélerer à une certaine vitesse

        v_x=vx_prec+deltaT*a_x;
        v_y=vy_prec+deltaT*a_y;
        pos_x=x_prec+deltaT*v_x;
        pos_y=y_prec+deltaT*v_y;
        pTime=time;

        x_prec=pos_x;
        vx_prec=v_x;
        y_prec=pos_y;
        vy_prec=v_y;





        // JUMP PART
        //if (x>2980 & x<3000) {pos_y=(positionY-1); y_prec=(positionY-1); System.out.println("OK");}
        //if (x>3000 & x<3010) {ay_prec=-20; a_y=-20; vy_prec=-150; v_y=-150; System.out.println("QUOI \n");}
       //setOnMouseClicked2(root,this);


        //GRAVITY
        if (pos_y<positionY) { a_y=a_y+gravity; ay_prec=ay_prec+gravity; }


        this.x=pos_x;
        this.y=pos_y;
        hitbox = new Rectangle2D(this.x,this.y,55,84); //length et width de hero 55 et 84
        //sprite.setX(this.x-xcam);
        //sprite.setY(this.y);

        if (compt==7) {
            //x+=15;
            //xaffichage=x-xcam; //x position du héro par rapport à un référentiel fixe, xaffichage position par rapport à la caméra
            //sprite.setX(xaffichage);
            //sprite.setY(this.y);

            if (a_y!=0&&v_y<0&&pos_y<positionY) {
                sprite.setViewport(new Rectangle2D(22,160,59,103));
            }
            else if (a_y!=0&&v_y>0&&pos_y<positionY) {
                sprite.setViewport(new Rectangle2D(94,164,67,94)); }
            else if(v_y==0) {
                    index=index%(maxIndex);
                    sprite.setViewport(new Rectangle2D(list[index*4],list[1+index*4],list[2+index*4],list[3+index*4]));
                    index++; }

            }
            compt=compt%7;

        compt++;
        //System.out.println("CECI EST X : "+x);
        //System.out.println("CECI EST Y : "+y);


    }

    public void jump() {
        if (pos_y>positionY-0.1) { //empêche le joueur de sauter dans les airs, il ne peut sauter que s'il touche le sol
        pos_y=(pos_y-1);
        y_prec=pos_y;
        ay_prec=-2000;
        a_y=-2000;
        vy_prec=-250;
        v_y=-250;
        }

    }

   public int getHitBox(Foe foe) {
        if (isInvicible==0) { //si le héro est vulnérable i.e. n'est pas invincible
            if ((pos_x >= foe.getXfoe()+35) && (pos_x <= foe.getXfoe() + foe.getLength()-35)) {
                if ((pos_y >=250-(foe.getYfoe()+15 + foe.getWidth()))&&pos_y<=250) {
                    System.out.println("CONTACT !");
                    return 1; //There's contact !
                }
            }
            if ((pos_x + length >= foe.getXfoe()+35) && (pos_x + length <= foe.getXfoe() + foe.getLength()-35)) {
                if ((pos_y >=250-(foe.getYfoe() +15+ foe.getWidth()))&&pos_y<=250) {
                    System.out.println("CONTACT !");
                    return 1; //There's contact !

                }
            }
        }
        else {
        return 0; } // 0 il n'y a pas contact
    return 0;
    }

   public boolean getHitBox2(Rectangle2D foe) {
        return(hitbox.intersects(foe));
   }

    public int getIsInvicible() {
        return isInvicible;
    }

    public void setIsInvicible(int isInvicible) {
        this.isInvicible = isInvicible;
    }

    public double getPositionY() {
        return positionY;
    }
}
