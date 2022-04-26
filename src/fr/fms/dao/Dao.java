package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface Dao<T> {
	public Connection connection = DBConnection.getConnection();
	public boolean create(T obj);		//ajout d'un nouveau 
	public T read(int id);				//renvoi un objet grâce à son id
	public boolean update(T obj);		//met à jour en bdd et renvoie true si tout est bon
	public boolean delete(T obj);		//supprime en bdd et renvoie true si tout est bon
	public ArrayList<T> selectAll(); 	//renvoi tous les objets d'une table
}
