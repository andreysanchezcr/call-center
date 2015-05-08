package cliente;


import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class ClienteChat
{
    private Socket socket;

    private PanelCliente panel;


    public static void main(String[] args)
    {
        new ClienteChat();
    }

  
    public ClienteChat()
    {
        try
        {
        	
            creaYVisualizaVentana();
            socket = new Socket("localhost", 4544);
            ObjectInputStream objeto_entrante= new ObjectInputStream(socket.getInputStream());
            ArrayList lista = (ArrayList) objeto_entrante.readObject();
            ControlCliente control = new ControlCliente(socket, panel,lista);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Crea una ventana, le mete dentro el panel para el cliente y la visualiza
     */
    private void creaYVisualizaVentana()
    {
        JFrame v = new JFrame();
        panel = new PanelCliente(v.getContentPane());
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
