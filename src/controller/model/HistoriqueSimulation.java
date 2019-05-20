package controller.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HistoriqueSimulation implements Serializable {
    
	private static HistoriqueSimulation instance;
	/**
	 * Méthode permettant de récupérer l'instance de l'historique simulation
	 * 
	 * @return une instance
	 */
	public static synchronized HistoriqueSimulation getInstance() {
	    if(HistoriqueSimulation.instance == null) {
	        HistoriqueSimulation.instance = new HistoriqueSimulation();
	    }

	    return HistoriqueSimulation.instance;
	}
	
    private List<String> data;
    
    /**
     * Constructeur de l'historique simulation
     */
    private HistoriqueSimulation() {
        this.data = new ArrayList<String>();
    }
    
    /**
     * Ajoute un string, représentant le résultat d'une simulation, à l'historique
     * 
     * @param result
     */
    public void add(String result) {
        this.data.add(result);
    }
    
    /**
     * Insère le tableau de byte dans le stream 
     * 
     * @param data
     */
    public static void fromByteArray(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois;
        Object obj = null;
        try {
            ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HistoriqueSimulation.instance = (HistoriqueSimulation)obj;
    }

    /** 
     * Méthode permettant de convertir un objet en un tableau de byte 
     * 
     * @return this.
     */
    public byte[] toByteArray() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
    
    /**
     * Méthode retournant la taille de la liste de données 
     * 
     * @return size (int)
     */
    public int size() {
    	return this.data.size();
    }
    
    /**
     * Méthode permettant le retour d'un objet (une simulation) sous forme de String
     * 
     * @param i un indice 
     * @return 
     */
    public String get(int i) {
    	return this.data.get(i);
    }
}
