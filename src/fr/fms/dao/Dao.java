package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface Dao<T> {
	/**
	 * Permet de récupérer la connexion à la base de données
	 */
	public Connection connection = DBConnection.getConnection();
	
	/**
	 * Méthode qui permet d'ajouter une nouvelle occurence en base
	 * @param obj
	 * @return true si tout s'est bien passé, false dans le cas contraire
	 */
	public boolean create(T obj);		
	
	/**
	 * Méthode qui permet récupérer une occurence correspondant à l'id passé en param
	 * @param id
	 * @return obj si trouvé, sinon obj null
	 */
	public T read(int id);				
	
	/**
	 * Méthode qui permet de mettre à jour un objet en base 
	 * @param obj
	 * @return true si tout s'est bien passé, false dans le cas contraire
	 */
	public boolean update(T obj);		
	
	/**
	 * Méthode qui permet de supprimer un objet en base
	 * @param obj
	 * @return true si tout s'est bien passé, false dans le cas contraire
	 */
	public boolean delete(T obj);		

	/**
	 * Méthode qui permet de sélectionner toutes les occurences d'une table
	 * @return ArrayList<T> correspondant 
	 */
	public ArrayList<T> selectAll(); 	
}
