import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {
    protected double x;
    protected double y;
    protected ImageView sprite;
    protected int attitude;
    protected int index; //index is between 0 and maxIndex-1
    protected int maxIndex;
    protected Rectangle2D hitbox;

    public AnimatedThing(double xpos, double ypos, String filename, Integer x1, Integer y1, Integer length, Integer width) {
        this.x=xpos;
        this.y=ypos;

        Image spriteSheet = new Image(filename);
        sprite = new ImageView(spriteSheet);
        sprite.setX(this.x);
        sprite.setY(this.y);
        sprite.setViewport(new Rectangle2D(x1,y1,length,width));
        hitbox = new Rectangle2D(x1,y1,length,width);
    }

    // GETTERS & SETTERS

    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D hitbox) {
        this.hitbox = hitbox;
    }

    abstract protected void update(long l, int xcam);

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
