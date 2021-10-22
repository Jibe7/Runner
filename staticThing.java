import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double x;
    private double y;
    private ImageView sprite;

    public staticThing(double xpos, double ypos, String fileName, Integer x, Integer y, Integer length, Integer width) {
        this.x=xpos;
        this.y=ypos;
        Image spriteSheet = new Image(fileName);
        this.sprite = new ImageView(spriteSheet);
        sprite.setViewport(new Rectangle2D(x,y, length, width));


    }
    public ImageView getSprite() {
        return sprite;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

}
