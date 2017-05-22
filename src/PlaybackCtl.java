import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by yinboli on 2/23/16.
 */
public class PlaybackCtl extends JPanel implements IView {
    private Doodle doodle;

    private JButton playBtn, startBtn, endBtn;
    private JSlider slider;

    PlaybackCtl(Doodle doodle_) {
        doodle = doodle_;

        this.setLayout(new FlowLayout());

        /* Play */
        playBtn = new JButton();
        playBtn.addActionListener(new playListener());

        try {
            Image tempImg = ImageIO.read(getClass().getResource("play.png"));
            Image customImg = tempImg.getScaledInstance(60, 25, java.awt.Image.SCALE_SMOOTH);
            playBtn.setIcon(new ImageIcon(customImg));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Slider */
        slider = new JSlider(0, doodle.getMaxTickValue(), 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider.getValue() > doodle.getTotalStroke()) {
                    slider.setValue(doodle.getTotalStroke());
                    doodle.setCurStroke(doodle.getTotalStroke());
                } else {
                    doodle.setCurStroke((int) slider.getValue());
                    doodle.setRedrawFlag(true);
                }
            }
        });

        /* Start */
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doodle.setStartPlayFlag(false);
                doodle.setPlayFlag(false);
                doodle.setRedrawFlag(false);
                doodle.setEndFlag(false);
                doodle.setStartFlag(true);
                doodle.setCurStroke(0);
                doodle.setEndFlag(false);
            }
        });

        /* End */
        endBtn = new JButton("End");
        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doodle.setStartPlayFlag(false);
                doodle.setPlayFlag(false);
                doodle.setRedrawFlag(false);
                doodle.setStartFlag(false);
                doodle.setEndFlag(true);
                doodle.setCurStroke(doodle.getTotalStroke());
                doodle.setEndFlag(false);
            }
        });

        this.add(playBtn);
        this.add(startBtn);
        this.add(slider);
        this.add(endBtn);
    }

    private class playListener implements ActionListener {
        private java.util.Timer timer;

        public void actionPerformed(ActionEvent e) {
            doodle.setCurStroke(0);
            doodle.setStartPlayFlag(false);
            timer = new java.util.Timer();
            timer.schedule(new playDoodle(), 0, 1000/10);
        }

        private class playDoodle extends java.util.TimerTask {
            public void run() {
                if (slider.getValue() == doodle.getTotalStroke()) {
                    timer.cancel();
                    timer.purge();
                }

                if (doodle.getCurStroke() < doodle.getTotalStroke()) {
                    doodle.setCurStroke(doodle.getCurStroke() + 1);
                }

                doodle.setPlayFlag(true); // Play a stroke
            }
        }
    }

    public void updateView() {
        slider.setValue(doodle.getCurStroke());
    }

}
