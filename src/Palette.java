import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by yinboli on 2/24/16.
 */
public class Palette extends JPanel implements IView {
    private Doodle doodle;

    JButton blackBtn, blueBtn, greenBtn, redBtn, yellowBtn, customBtn,
            thinBtn, midBtn, thickBtn;

    Palette (Doodle doodle_) {
        doodle = doodle_;

        this.setLayout(new BorderLayout());

        Box vertBox = Box.createVerticalBox();

        JPanel colorBox = new JPanel();
        colorBox.setBorder(BorderFactory.createTitledBorder("COLOR"));
        colorBox.setLayout(new GridLayout(3,2));

        JPanel strokeBox = new JPanel();
        strokeBox.setBorder(BorderFactory.createTitledBorder("STROKE"));
        strokeBox.setLayout(new GridLayout(3,1));

        blackBtn = new JButton();
        blackBtn.setSize(50,50);
        blackBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("black.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            blackBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        blueBtn = new JButton();
        blueBtn.setSize(50,50);
        blueBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("blue.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            blueBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        greenBtn = new JButton();
        greenBtn.setSize(50,50);
        greenBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("green.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            greenBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        redBtn = new JButton();
        redBtn.setSize(50,50);
        redBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("red.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            redBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        yellowBtn = new JButton();
        yellowBtn.setSize(50,50);
        yellowBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("yellow.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            yellowBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        customBtn = new JButton();
        customBtn.setSize(50,50);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("custom.png"));
            Image customImg = tempImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            customBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doodle.setColor(JColorChooser.showDialog(null, "Custom Color", Color.WHITE));
            }
        });

        colorBox.add(blackBtn);
        colorBox.add(blueBtn);
        colorBox.add(greenBtn);
        colorBox.add(redBtn);
        colorBox.add(yellowBtn);
        colorBox.add(customBtn);

        thinBtn = new JButton();
        thinBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("thin.png"));
            Image customImg = tempImg.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
            thinBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        midBtn = new JButton();
        midBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("mid.png"));
            Image customImg = tempImg.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
            midBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        thickBtn = new JButton();
        thickBtn.addActionListener(actionListener);
        try {
            Image tempImg = ImageIO.read(getClass().getResource("thick.png"));
            Image customImg = tempImg.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
            thickBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        strokeBox.add(thinBtn);
        strokeBox.add(midBtn);
        strokeBox.add(thickBtn);

        vertBox.add(colorBox);
        vertBox.add(strokeBox);
        this.add(vertBox);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == blackBtn) {
                doodle.setColor(Color.black);
            } else if (e.getSource() == blueBtn) {
                doodle.setColor(Color.blue);
            } else if (e.getSource() == greenBtn) {
                doodle.setColor(Color.green);
            } else if (e.getSource() == redBtn) {
                doodle.setColor(Color.red);
            } else if (e.getSource() == yellowBtn) {
                doodle.setColor(Color.yellow);
            } else if (e.getSource() == thinBtn) {
                doodle.setWidth(1);
            } else if (e.getSource() == midBtn) {
                doodle.setWidth(4);
            } else if (e.getSource() == thickBtn) {
                doodle.setWidth(8);
            }
        }
    };

    public void updateView() {

    }
}
