package cliente;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel para mostrar la conversación y pedir al usuario el texto que quiere
 * enviar.
 * @author Chuidiang
 */
public class PanelCliente
{
    /** Scroll */
    private JScrollPane scroll;

    /** Area para mostrar la conversación */
    private JTextArea textArea;

    /** Para pedir el texto al usuario */
    private JTextField textField;

    /** Botón para enviar el texto */
    private JButton boton;


    public PanelCliente(Container contenedor)
    {
        contenedor.setLayout(new BorderLayout());
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea);

        JPanel panel = new JPanel(new FlowLayout());
        textField = new JTextField(50);
        boton = new JButton("Enviar");
        panel.add(textField);
        panel.add(boton);

        contenedor.add(scroll, BorderLayout.CENTER);
        contenedor.add(panel, BorderLayout.SOUTH);
    }


    public void addActionListener(ActionListener accion)
    {
        textField.addActionListener(accion);
        boton.addActionListener(accion);

    }


    public void addTexto(String texto)
    {
        textArea.append(texto);
    }

 
    public String getTexto()
    {
        String texto = textField.getText();
        textField.setText("");
        return texto;
    }
}
