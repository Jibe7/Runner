import javafx.geometry.Rectangle2D;

public class Foe extends AnimatedThing {
    private double xfoe;
    private double yfoe;
    private double distancefoe=1000;
    private double deltaT;
    private long pTime=0;
    private double length=72;
    private double width=104;


    //CONSTRUCTOR
    public Foe(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width) {
        super(xpos,ypos,filename,x1,y1,length,width);
        xfoe=xpos;
        yfoe=ypos;
    }

    //METHOD
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

    public void update(long time,int xcam) { //on va afficher l'enemie tous les distancefoe

        //if (xcam>xfoe-1000) {
            //xfoe+=0;
        //}
        }
        //sprite.setViewport(new Rectangle2D(144,19,72,104));

        //if (compt==7) {
            //x+=15;
            //xaffichage=x-xcam; //x position du héro par rapport à un référentiel fixe, xaffichage position par rapport à la caméra
            //sprite.setX(xaffichage);
            //sprite.setY(this.y);



            //compt=compt%7;
       // }
       // compt++;


    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
}

