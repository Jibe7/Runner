import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double x;
    private double y;
    private ImageView sprite;

    public ImageView getSprite() {
        return this.sprite;
    }
    public staticThing(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width) {
        this.x=xpos;
        this.y=ypos;

        Image spriteSheet = new Image(filename);
        sprite = new ImageView(spriteSheet);
        sprite.setX(this.x);
        sprite.setY(this.y);
        sprite.setViewport(new Rectangle2D(x1,y1,length,width));

    }

    public void modSprite(int x1,int y1,int length, int width) {
        sprite.setViewport(new Rectangle2D(x1,y1,length,width));
    }
}
