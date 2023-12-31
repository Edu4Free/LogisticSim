package view;

import server.HTTPClientConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Panel mit Textfeld zur Entgegennahme von Konsolenbefehlen
 */
public class CommandoPanel extends JPanel implements KeyListener {
    private JTextField textField;
    private JTextArea textArea;

    public CommandoPanel(JTextArea textArea, String hostAdress)
    {
        this.textArea = textArea;

        String adress = null;

        if(hostAdress==null)
            adress = "localhost";
        else
            adress = hostAdress;

        setLayout(new GridLayout(1,1));

        textField = new JTextField("http://"+adress+":8000/weather?country=DE&postcode=50679&city=Koeln&data=all");
        textField.setFont(new Font("Dialog", Font.PLAIN, 14));
        // http://www.it-host.de/jserv/java/index.jsp?zugriff=wvsfst

        textField.addKeyListener(this);

        add(textField);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("keyPressed");

        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            System.out.println("ENTER");

            HTTPClientConnection connection = new HTTPClientConnection();

            System.out.println(textField.getText());

            String result = connection.sendRequest(textField.getText());

            textArea.setText(textArea.getText()+"/"+result+"\n");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
