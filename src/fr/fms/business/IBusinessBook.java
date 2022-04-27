package fr.fms.business;

import java.util.ArrayList;

import fr.fms.entities.Book;
import fr.fms.entities.Thematic;

public abstract interface IBusinessBook {
	
	/**
	 * Méthode qui retourne une liste de tous les livres
	 * @return
	 */
	public ArrayList<Book> selectAllBooks();
	
	/**
	 * Méthode qui retourne une liste de tous les livres d'une thématique
	 * @return 
	 */
	public ArrayList<Book> selectAllBooksByThematic(int id);
	
	/**
	 * Méthode qui retourne une liste des thématiques
	 * @return
	 */
	public ArrayList<Thematic> selectThematics();
	
	
	
	
}
