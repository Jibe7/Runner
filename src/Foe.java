import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing {
    private double xfoe;
    private double yfoe;
    private double deltaT;
    private double length=72;
    private double width=104;



    //CONSTRUCTOR
    public Foe(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width) {
        super(xpos,ypos,filename,x1,y1,length,width);
        xfoe=xpos;
        yfoe=ypos;
    }

    // EMPTY UPDATE FUNCTION, EVERYTHING CONCERNING THE FOES IS DONE IN THE GAMESCENE UPDATE FUNCTION
    public void update(long time,int xcam) {

    }

    //SETTERS & GETTERS
    public void setXfoe(double xfoe) {
        this.xfoe = xfoe;
    }

    public void setYfoe(double yfoe) {
        this.yfoe = yfoe;
    }

    public double getXfoe() {
        return xfoe;
    }

    public double getYfoe() {
        return yfoe;
    }

    public double getLength() {
        return length;
    }
    public double getWidth() {
        return width;
    }
}


