import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by yinboli on 2/23/16.
 */
public class MenuBar extends JMenuBar implements IView {
    private Doodle doodle;

    //JMenuBar menuBar;
    private JMenu menuFile, menuView;
    private JMenuItem menuItemNew, menuItemSave, menuItemLoad, menuItemQuit, menuItemFull, menuItemFit;

    MenuBar(Doodle doodle_) {
        doodle = doodle_;

        menuFile = new JMenu("File");

        /* New */
        menuItemNew = new JMenuItem("New");
        menuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doodle.strokeAry = new ArrayList<ArrayList>();
                doodle.setRedrawFlag(false);
                doodle.setPlayFlag(false);
                doodle.setStartPlayFlag(false);
                doodle.setStartFlag(false);
                doodle.setEndFlag(false);
                doodle.setCurStroke(0);
                doodle.setTotalStroke(0);
            }
        });
        menuFile.add(menuItemNew);

        /* Save */
        menuItemSave = new JMenuItem("Save");
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sb = doodleToString();
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Save Doodle");
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try (FileWriter fw = new FileWriter(fc.getSelectedFile()+".doodle")) {
                        fw.write(sb.toString());
                        fw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        menuFile.add(menuItemSave);

        /* Load */
        menuItemLoad = new JMenuItem("Load");
        menuFile.add(menuItemLoad);

        /* Quit */
        menuItemQuit = new JMenuItem("Quit");
        menuItemQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuFile.add(menuItemQuit);

        menuView = new JMenu("View");
        menuItemFull = new JMenuItem("Full");
        menuView.add(menuItemFull);
        menuItemFit = new JMenuItem("Fit");
        menuView.add(menuItemFit);

        //frame.setJMenuBar(menuBar);
        this.add(menuFile);
        this.add(menuView);
    }

    public String doodleToString() {
        String sb = "";

        for (int i=0; i<doodle.strokeAry.size(); i++) {
            ArrayList<Line> tempStroke = doodle.strokeAry.get(i);
            for (int j=0; j<tempStroke.size(); j++) {
                Line tempLine = tempStroke.get(j);
                sb = sb + tempLine.getSourceX() + " " +tempLine.getSourceY() + " " +
                        tempLine.getDestX() + " " + tempLine.getDestY() + " \n";
            }
            sb = sb + "\n";
        }

        return sb;
    }


    public void updateView() {

    }
}
