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
	
	/**
	 * Méthode qui permet d'ajouter un livre au panier
	 * @param book
	 */
	public void addToCart(Book book);
	
	/**
	 * Méthode qui permet de supprimer un livre du panier
	 * @param book
	 */
	public boolean removeFromCart(int id);
	
	/**
	 * Méthode qui retourne la liste des livres du panier
	 * @return
	 */
	public ArrayList<Book> getCart();
	
	/**
	 * Méthode qui retourne un livre correspondant à l'id
	 * @param id
	 * @return
	 */
	public Book getOneBook(int id);
	
}
