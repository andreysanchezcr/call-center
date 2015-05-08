package servidor;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * Hilo encargado de atender a un cliente.
 * @author Chuidiang
 */
public class HiloDeCliente implements Runnable, ListDataListener
{
    /** Lista en la que se guarda toda la charla */
    private DefaultListModel charla;

    /** Socket al que está conectado el cliente */
    private Socket socket;

    /** Para lectura de datos en el socket */
    private DataInputStream dataInput;

    /** Para escritura de datos en el socket */
    private DataOutputStream dataOutput;
    ArrayList lista = new ArrayList();
    ObjectOutputStream dataObject; 

    private ServidorChat servidor;
    public HiloDeCliente(DefaultListModel charla, Socket socket, ArrayList lista,ServidorChat servidor) throws IOException
    {
    	
    	this.servidor=servidor;
        this.charla = charla;
        this.socket = socket;
        this.lista=lista;
        try
        {
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataObject = new ObjectOutputStream(socket.getOutputStream());
            charla.addListDataListener(this);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
        	dataObject.writeObject(lista);
        }catch(Exception e){
        	System.out.println("Error al enviar la lista");
        	
        }
        
    }


    public void run()
    {
        try
        {
            while (true)
            {
                int indice = dataInput.readInt();
                
                if(indice==999){
                	servidor.finalizar=true;
                	
                }
                {
                    charla.addElement(Integer.toString(indice));
                    System.out.println("Se ha presionado: "+indice);
                    lista.remove(indice);
                	lista.add(indice, "Ceroooooo");
                    
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void intervalAdded(ListDataEvent e)
    {
        String texto = (String) charla.getElementAt(e.getIndex0());
        try
        {
            //dataOutput.writeUTF(texto);
        	dataOutput.writeInt(Integer.parseInt(texto));
        } catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
    }


	@Override
	public void contentsChanged(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
