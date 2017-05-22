import java.awt.*;

/**
 * Created by yinboli on 2/24/16.
 */
public class Line {
    private Color color;
    private int width;
    private int sourceX, sourceY, destX, destY;

    public Line(Color color_, int width_, int sourceX_, int sourceY_, int destX_, int destY_) {
        color = color_;
        width = width_;
        sourceX = sourceX_;
        sourceY = sourceY_;
        destX = destX_;
        destY = destY_;
    }

    public void setColor(Color color_) {
        color = color_;
    }

    public Color getColor() {
        return color;
    }

    public void setWidth(int width_) {
        width = width_;
    }

    public int getWidth() {
        return width;
    }

    public void setSourceX(int sourceX_) {
        sourceX = sourceX_;
    }

    public int getSourceX() {
        return sourceX;
    }

    public void setSourceY(int sourceY_) {
        sourceX = sourceY_;
    }

    public int getSourceY () {
        return sourceY;
    }

    public void setDestX(int destX_) {
        destX = destX_;
    }

    public int getDestX() {
        return destX;
    }

    public void setDestY(int destY_) {
        destY = destY_;
    }

    public int getDestY() {
        return destY;
    }
}
