import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {
    protected double x;
    protected double y;
    protected ImageView sprite;
    protected int attitude;
    protected int index; //index entre 0 et maxIndex-1
    protected int maxIndex;



    public AnimatedThing(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width) {
        this.x=xpos;
        this.y=ypos;

        Image spriteSheet = new Image(filename);
        sprite = new ImageView(spriteSheet);
        sprite.setX(this.x);
        sprite.setY(this.y);
        sprite.setViewport(new Rectangle2D(x1,y1,length,width));

    }

    abstract protected void update(long l,int xcam);

    public int getAttitude() {
        return attitude;
    }

    public int getMaxIndex() {
        return maxIndex;
    }

    public ImageView getSprite() {
        return this.sprite;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public int getIndex() {
        return index;
    }
}
