import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Created by yinboli on 2/18/16.
 */
public class DoodleArea extends JPanel implements IView {
    private Doodle doodle;
    private Image image;
    private Graphics2D g2;
    private int oldX, oldY, curX, curY;
    private boolean isDrawing = false; // to avoid mouse press then release
    private java.util.Timer timer;

    private ArrayList<Line> stroke;
    private Line line;

    public DoodleArea(Doodle doodle_) {
        doodle = doodle_;

        //setPreferredSize(new Dimension(200,200));
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
                stroke = new ArrayList<Line>();
            }

            public void mouseReleased(MouseEvent e) {
                if (isDrawing) {
                    doodle.addStroke(stroke);
                    doodle.setTotalStroke(doodle.getTotalStroke() + 1);
                    doodle.setCurStroke(doodle.getCurStroke() + 1);
                    //doodle.majorTickSpacing = doodle.getMaxTickValue() / doodle.getTotalStroke();
                    doodle.setRedrawFlag(true);
                }
                isDrawing = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                isDrawing = true;
                doodle.setRedrawFlag(false);

                curX = e.getX();
                curY = e.getY();

                line = new Line(doodle.getColor(), doodle.getWidth(), oldX, oldY, curX, curY);
                stroke.add(line);

                if (g2 != null) {
                    g2.drawLine(oldX, oldY, curX, curY);
                    repaint();
                    oldX = e.getX();
                    oldY = e.getY();
                }
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setPaint(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        repaint();
    }

    public void updateView() {
        g2.setPaint(doodle.getColor());
        g2.setStroke(new BasicStroke(doodle.getWidth()));

        if (doodle.getRedrawFlag()) {
            clear();
            for (int i=0; i<doodle.getCurStroke(); i++) {
                ArrayList<Line> tempStroke = doodle.strokeAry.get(i);
                for (int j=0; j<tempStroke.size(); j++) {
                    Line tempLine = tempStroke.get(j);
                    g2.setPaint(tempLine.getColor());
                    g2.setStroke(new BasicStroke(tempLine.getWidth()));
                    g2.drawLine(tempLine.getSourceX(), tempLine.getSourceY(), tempLine.getDestX(), tempLine.getDestY());
                }
            }
            doodle.setRedrawFlag(false);
        }

        if (doodle.getPlayFlag()) {
            if (doodle.getStartPlayFlag()) {
                clear();
                doodle.setStartPlayFlag(true);
            }

            ArrayList<Line> tempStroke = doodle.strokeAry.get(doodle.getCurStroke()-1);
            for (int i=0; i<tempStroke.size(); i++) {
                Line tempLine = tempStroke.get(i);
                g2.setPaint(tempLine.getColor());
                g2.setStroke(new BasicStroke(tempLine.getWidth()));
                g2.drawLine(tempLine.getSourceX(), tempLine.getSourceY(), tempLine.getDestX(), tempLine.getDestY());
            }

            doodle.setPlayFlag(false);
        }

        if (doodle.getStartFlag()) {
            clear();
            doodle.setStartFlag(false);
        }

        if (doodle.getEndFlag()) {
            for (int i=0; i<doodle.strokeAry.size(); i++) {
                ArrayList<Line> tempStroke = doodle.strokeAry.get(i);
                for (int j=0; j<tempStroke.size(); j++) {
                    Line tempLine = tempStroke.get(j);
                    g2.setPaint(tempLine.getColor());
                    g2.setStroke(new BasicStroke(tempLine.getWidth()));
                    g2.drawLine(tempLine.getSourceX(), tempLine.getSourceY(), tempLine.getDestX(), tempLine.getDestY());
                }
            }
            doodle.setEndFlag(false);
        }

        if (doodle.getNewFlag()) {
            clear();
            doodle.setNewFlag(false);
        }
    }
}
