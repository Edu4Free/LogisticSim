package menu;

import server.Webserver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 */
public class MenuPanel extends JPanel implements ActionListener {

    private JButton startServer, stopServer,clear;
    private Border defaultBorder;
    private JTextArea textArea;

    private JComboBox<String> models = new JComboBox<>(new String[]{"Foerderband", "Palettenlager"});

    private Webserver webserver = null;

    public MenuPanel(JTextArea textArea)
    {
        this.textArea = textArea;

        try { webserver = new Webserver(); }
        catch (IOException e) {  throw new RuntimeException(e); }

        ButtonGroup buttonGroup = new ButtonGroup();

        startServer = new JButton("Start Server");
        stopServer = new JButton("Stop Server");
        clear = new JButton("Clear");

        defaultBorder = startServer.getBorder();

        startServer.setOpaque(true);
        stopServer.setOpaque(true);
        clear.setOpaque(true);

        buttonGroup.add(startServer);
        buttonGroup.add(stopServer);

        models.setFont(new Font("Dialog", Font.PLAIN, 14));
        startServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        stopServer.setFont(new Font("Dialog", Font.PLAIN, 14));
        clear.setFont(new Font("Dialog", Font.PLAIN, 14));

        models.addActionListener(this);
        startServer.addActionListener(this);
        stopServer.addActionListener(this);
        clear.addActionListener(this);

        add(models);
        add(startServer);
        add(stopServer);
        add(clear);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        System.out.println("Button "+e.getActionCommand()+" gedrueckt");

        if(e.getActionCommand().contains("Start") || e.getActionCommand().contains("Stop"))
        {
            startServer.setBorder(defaultBorder);
            stopServer.setBorder(defaultBorder);
            ((JButton)e.getSource()).setBorder(BorderFactory.createLineBorder(Color.green, 3));
        }

        if(e.getActionCommand().contains("Start"))
        {
            try {
                webserver.startServer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        else
            if(e.getActionCommand().contains("Stop"))
            {
                webserver.stopServer();
            }
            else
                if(e.getActionCommand().contains("Clear"))
                {
                    textArea.setText("Ausgaben:\n");
                }

        if(e.getActionCommand().contains("comboBoxChanged"))
        {
            JComboBox cbox = (JComboBox)e.getSource();

            System.out.println(cbox.getSelectedItem());
        }

        repaint();
    }
}
