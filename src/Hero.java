import javafx.geometry.Rectangle2D;

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
    private final double positionY=200; //position of the road
    private double pos_y=positionY;
    private double y_prec=positionY;
    private double v_y=0;
    private double vy_prec=0;
    private double a_y=0;
    private double ay_prec=0;
    private double deltaT;
    private long pTime=0;
    private double length=55;
    private double width=84;
    private int numberOfLives=4;

    // CONSTRUCTOR
    public Hero(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width, int maxI,int[] Lh) {
        super(xpos,ypos,filename,x1,y1,length,width);
        list = Lh;
        index=0;
        maxIndex=maxI;
        compt=0;
    }

    // HERO UPDATE
    // This function make the hero running, jumping, falling, reaching the ground and update his hitbox and his display on the screen
    public void update(long time,int xcam) { // this code is to make the hero run

        if (pTime==0) pTime=time;

        if (vy_prec!=0&&y_prec>=(positionY-0.001)) {vy_prec=0; a_y=0; ay_prec=0; v_y=0; y_prec=positionY;} // if the hero falls on the floor we stop him
        deltaT = 1e-9*(time-pTime);
        a_x=ax_prec;
        a_y=ay_prec;

        if (v_x>600) {v_x=vx_prec; a_x=0; ax_prec=0;} // we stop the acceleration of the hero to keep him at the same speed
        else {v_x=vx_prec+deltaT*a_x;} // we accelerate the hero

        v_x=vx_prec+deltaT*a_x;
        v_y=vy_prec+deltaT*a_y;
        pos_x=x_prec+deltaT*v_x;
        pos_y=y_prec+deltaT*v_y;
        pTime=time;

        x_prec=pos_x;
        vx_prec=v_x;
        y_prec=pos_y;
        vy_prec=v_y;

        //GRAVITY
        if (pos_y<positionY) { a_y=a_y+gravity; ay_prec=ay_prec+gravity; } // If the hero is in the air only the gravity is applied to him (we do not put any air friction)
        // AFTER CALCULATION WE UPDATE X,Y POSITION AND THE HITBOX
        this.x=pos_x;
        this.y=pos_y;
        hitbox = new Rectangle2D(this.x,this.y,length,width);

        // THIS COUNTER CALLED 'compt' IS USED TO MAKE THE HERO ANIMATION SLOWER THAT WHAT IT WOULD BE WITHOUT IT
        if (compt==7&&compt!=77&&compt!=99) { // compt is set to 77 and 99 when the hero is dead in order to execute some specific features in the GameScene class
            if (a_y!=0&&v_y<0&&pos_y<positionY) { // the hero jumps
                sprite.setViewport(new Rectangle2D(22,160,59,103));
            }
            else if (a_y!=0&&v_y>0&&pos_y<positionY) { // the hero falls
                sprite.setViewport(new Rectangle2D(94,164,67,94)); }
            else if(v_y==0) { // the hero runs
                    index=index%(maxIndex);
                    sprite.setViewport(new Rectangle2D(list[index*4],list[1+index*4],list[2+index*4],list[3+index*4]));
                    index++; }
            }
            compt=compt%7;
        compt++;
    }

    // JUMP FUNCTION : IF THE HERO IS ON THE GROUND, WE GIVE HIM NEGATIVE ACCELERATION AND SPEED SO HE GOES UP ON THE SCREEN
    public void jump() {
        if (pos_y>positionY-0.1) { // This test allows the hero to jump only if he is on the ground
        pos_y=(pos_y-1);
        y_prec=pos_y;
        ay_prec=-2000;
        a_y=-2000;
        vy_prec=-270;
        v_y=-270;
        }
    }

    //GETTERS & SETTERS
    public void setList(int[] L) {
        list=L;
    }

   public boolean getHitBox2(Rectangle2D foe) {
        return(hitbox.intersects(foe));
   }

    public double getPositionY() {
        return positionY;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setA_x(double a_x) {
        this.a_x = a_x;
    }

    public void setAx_prec(double ax_prec) {
        this.ax_prec = ax_prec;
    }

    public void setV_x(double v_x) {
        this.v_x = v_x;
    }

    public void setVx_prec(double vx_prec) {
        this.vx_prec = vx_prec;
    }

    public void setV_y(double v_y) {
        this.v_y = v_y;
    }

    public void setVy_prec(double vy_prec) {
        this.vy_prec = vy_prec;
    }

    public void setA_y(double a_y) {
        this.a_y = a_y;
    }

    public void setAy_prec(double ay_prec) {
        this.ay_prec = ay_prec;
    }

    public void setCompt(int compt) {
        this.compt = compt;
    }

    public int getCompt() {
        return compt;
    }

    public void setPos_x(double pos_x) {
        this.pos_x = pos_x;
    }

    public void setPos_y(double pos_y) {
        this.pos_y = pos_y;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

}
