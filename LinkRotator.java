import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.net.*;

public class LinkRotator extends JFrame
        implements Runnable, ActionListener {

    String[] pageTitle = new String[6];
    URI[] pageLink = new URI[6];
    int current = 0;
    Thread runner;
    JLabel siteLabel = new JLabel();

    public LinkRotator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        FlowLayout flo = new FlowLayout();
        setLayout(flo);
        add(siteLabel);
        pageTitle = new String[] {
                "Sportal",
                "Facebook",
                "Youtube",
                "SoftUni",
                "GitHub ",
                "Twitch"
        };
        pageLink[0] = getURI("https://sportal.bg/");
        pageLink[1] = getURI("https://www.facebook.com/");
        pageLink[2] = getURI("https://www.youtube.com/");
        pageLink[3] = getURI("https://softuni.bg/");
        pageLink[4] = getURI("https://github.com/");
        pageLink[5] = getURI("https://www.twitch.tv/");
        Button visitButton = new Button("Visit Site");
        visitButton.addActionListener(this);
        add(visitButton);
        setVisible(true);
        start();
    }

    private URI getURI(String urlText) {
        URI pageURI = null;
        try {
            pageURI = new URI(urlText);
        } catch (URISyntaxException ex) {

        }
        return pageURI;
    }

    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        Thread thisThread = Thread.currentThread();
        while (runner == thisThread) {
            current++;
            if (current > 5) {
                current = 0;
            }
            siteLabel.setText(pageTitle[current]);
            repaint();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exc) {

            }
        }
    }

    public void actionPerformed(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        if (pageLink[current] != null) {
            try {
                desktop.browse(pageLink[current]);
                runner = null;
                System.exit(0);
            } catch (IOException exc) {

            }
        }
    }

    public static void main(String[] arguments) {
        new LinkRotator();
    }
}
