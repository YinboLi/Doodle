import javax.swing.*;
import java.awt.*;

/**
 * Created by yinboli on 2/23/16.
 */

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Doodle");

        Doodle doodle = new Doodle();

        DoodleArea doodleArea = new DoodleArea(doodle);
        doodle.addView(doodleArea);

        MenuBar menuBar = new MenuBar(doodle);
        doodle.addView(menuBar);

        PlaybackCtl playbackCtl = new PlaybackCtl(doodle);
        doodle.addView(playbackCtl);

        Palette palette = new Palette(doodle);
        doodle.addView(palette);

        // Create the window
        Container mainPanel = frame.getContentPane();
        frame.setJMenuBar(menuBar);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(doodleArea, BorderLayout.CENTER);
        mainPanel.add(playbackCtl, BorderLayout.SOUTH);
        mainPanel.add(palette, BorderLayout.WEST);

        frame.setSize(800, 600);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
