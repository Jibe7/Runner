public class Camera {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Camera(double xpos,double ypos) {
        x=xpos;
        y=ypos;
    }

    @Override
    public String toString() {
        return (x + ", " + y );
    }
}
