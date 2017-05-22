import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by yinboli on 2/18/16.
 */

interface IView {
    public void updateView();
}

public class Doodle {
    final private int maxTickValue = 20;
    public int majorTickSpacing;

    private Color color = Color.black;
    private int width = 1;
    private int totalStroke = 0, curStroke = 0;
    private boolean redrawFlag = false;
    private boolean playFlag = false;
    private boolean startPlayFlag = false; // call clear() once start play
    private boolean startFlag = false; // go to the start of the doodle once "start" is clicked
    private boolean endFlag = false; // go to the end of the doodle once "end" is clicked
    private boolean newFlag = false;

    private ArrayList<IView> views = new ArrayList<IView>();

    public ArrayList<ArrayList> strokeAry = new ArrayList<ArrayList>();

    public void addView(IView view) {
        views.add(view);
        //view.updateView();
    }

    // a stroke is an array of lines
    public void addStroke(ArrayList<Line> stroke) {
        strokeAry.add(stroke);
    }

    public int getMaxTickValue() {
        return maxTickValue;
    }

    public void setColor(Color c) {
        color = c;
        notifyObservers();
    }

    public Color getColor() {
        return color;
    }

    public void setWidth(int w) {
        width = w;
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public void setTotalStroke(int ts) {
        totalStroke = ts;
        notifyObservers();
    }

    public int getTotalStroke() {
        return totalStroke;
    }

    public void setCurStroke(int cs) {
        curStroke = cs;
        notifyObservers();
    }

    public int getCurStroke() {
        return curStroke;
    }

    public void setRedrawFlag(boolean rf) {
        redrawFlag = rf;
        if (rf == true) notifyObservers();
    }

    public boolean getRedrawFlag() {
        return redrawFlag;
    }

    // Play Doodle
    public void setPlayFlag(boolean pf) {
        playFlag = pf;
        if (pf == true) {
            setRedrawFlag(false);
            notifyObservers();
        }
    }

    public boolean getPlayFlag() {
        return playFlag;
    }

    public void setStartPlayFlag(boolean sp) {
        startPlayFlag = sp;
    }

    public boolean getStartPlayFlag() {
        return startPlayFlag;
    }

    public void setStartFlag(boolean sf) {
        startFlag = sf;
        if (sf = true) {
            notifyObservers();
        }
    }

    public boolean getStartFlag() {
        return startFlag;
    }

    public void setEndFlag(boolean ef) {
        endFlag = ef;
        if (ef = true) {
            notifyObservers();
        }
    }

    public boolean getEndFlag() {
        return endFlag;
    }

    public void setNewFlag(boolean nf) {
        newFlag = nf;
        if (nf = true) {
            notifyObservers();
        }
    }

    public boolean getNewFlag() {
        return newFlag;
    }

    // notify the IView observer
    private void notifyObservers() {
        this.views.forEach(IView::updateView);
    }
}
