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
    private double v_y;
    private double vy_prec;
    private double a_y;
    private double deltaT;
    private double fm=0.7;
    private long pTime=0;
    private  double km=3;

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
        x=xpos;
        y=ypos;
    }

    public void update(long time,Hero hero) {
        //x+=1;
        if (pTime==0) pTime=time;

        deltaT = 1e-9*(time-pTime);
        a_x=km*(hero.getX()-100-x_prec)-fm*vx_prec;
        a_y=2*km*(hero.getY()-250-y_prec)-fm*vy_prec;
        v_x=vx_prec+deltaT*a_x;
        v_y=vy_prec+deltaT*a_y;
        pos_x=x_prec+deltaT*v_x;
        pos_y=y_prec+deltaT*v_y;
        pTime=time;

        x_prec=pos_x;
        vx_prec=v_x;
        y_prec=pos_y;
        vy_prec=v_y;
        x=pos_x;
        y=pos_y;

    }

    @Override
    public String toString() {
        return (x + ", " + y );
    }
}
