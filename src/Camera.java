public class Camera {
    private double x;
    private double y;
    private double pos_x=0;
    private double x_prec=0;
    private double v_x=0;
    private double vx_prec=0;
    private double a_x;
    private double pos_y=0;
    private double y_prec=0;
    private double v_y=0;
    private double vy_prec=0;
    private double a_y=0;
    private double deltaT;
    private double fm=0.7;
    private long pTime=0;
    private  double km=3*fm;

    public void update(long time,Hero hero) { //implemented the spring on the X-axis but I didn't achieve to do it on the Y-axis (was not stable)
        if (pTime==0) pTime=time;
        deltaT = 1e-9*(time-pTime);
        a_x=km*(hero.getX()-100-x_prec)-fm*vx_prec;
        v_x=vx_prec+deltaT*a_x;
        pos_x=x_prec+deltaT*v_x;
        pos_y=hero.getY()-hero.getPositionY();
        pTime=time;
        if (pos_y<-GameScene.getUpShiftBg()-0.001) {this.y=-GameScene.getUpShiftBg();}
        else if (pos_y>GameScene.getDownShiftBg()-0.001) {this.y=GameScene.getDownShiftBg();}
        else {this.y=pos_y;}
        this.x=pos_x;
        x_prec=pos_x;
        vx_prec=v_x;
        y_prec=pos_y;
        vy_prec=v_y;
    }

    //GETTERS & SETTERS
    @Override
    public String toString() {
        return (x + ", " + y );
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Camera(double xpos, double ypos) {
        this.x=xpos;
        this.y=ypos;
    }
}
