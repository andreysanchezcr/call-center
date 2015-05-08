package servidor;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * Servidor de chat.
 * Acepta conexiones de clientes, crea un hilo para atenderlos, y espera la
 * siguiente conexion.
 * @author Chuidiang
 *
 */
public class ServidorChat
{
	ArrayList lista = new ArrayList();
    /** Lista en la que se guaradara toda la conversacion */
    private DefaultListModel charla = new DefaultListModel();
    boolean finalizar=false;
    /**
     * Instancia esta clase.
     * @param args
     */
    public static void main(String[] args)
    {
        new ServidorChat();
    }

    /**
     * Se mete en un bucle infinito para ateder clientes, lanzando un hilo
     * para cada uno de ellos.
     */
    public ServidorChat()
    {
    	lista.add("CERO");
    	lista.add("uno");
    	lista.add("dos");
    	lista.add("tres");
    	lista.add("cuatro");
    	lista.add("CINCO");
        try
        {
            ServerSocket socketServidor = new ServerSocket(4544);
            while (true)
            {
            	if(finalizar){
            		socketServidor.close();
            		System.out.println("Se ha detenido el servidor");
            		System.out.println("El resultado de la lista es el sigueinte: (guardando);");
            		for(int i=0;i<lista.size();i++){
            			System.out.println(lista.get(i));
            		}
            	}
                Socket cliente = socketServidor.accept();
                Runnable nuevoCliente = new HiloDeCliente(charla, cliente,lista,this);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
