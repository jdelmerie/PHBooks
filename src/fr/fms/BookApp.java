package fr.fms;

import java.util.ArrayList;
import java.util.Scanner;
import fr.fms.business.IBusinessBookImpl;
import fr.fms.entities.Book;
import fr.fms.entities.Thematic;

public class BookApp {

	private static Scanner scan = new Scanner(System.in);
	private static IBusinessBookImpl bookJob = new IBusinessBookImpl();

	public static void main(String[] args) {

		welcome();

		int choice = 0;

		while (choice != 8) {
			try {
				displayMenu();
				switch (choice = input()) {
				case 1:
					System.out.println("addArticle()");
					break;
				case 2:
					System.out.println("removeArticle()");
					break;
				case 3:
					System.out.println("displayCart()");
					break;
				case 4:
					displayBooks();
					break;
				case 5:
					displayThematics();
					break;
				case 6:
					displayBooksByThematic();
					break;
				case 7:
					System.out.println("connexion");
					break;
				case 8:
					System.out.println("déconnexion");
					break;
				default:
					System.out.println("Mauvaise saisie, recommencez !");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void displayMenu() {
		System.out.println("Que souhaitez-vous faire ? [Saisir le chiffre correspondant]");
		System.out.println("[1] - Ajouter un livre au panier");
		System.out.println("[2] - Supprimer un livre du panier");
		System.out.println("[3] - Afficher le panier et passer commande");
		System.out.println("[4] - Afficher tous les livres de la librarie");
		System.out.println("[5] - Afficher toutes les thématiques");
		System.out.println("[6] - Afficher les articles par thématique");
		System.out.println("[7] - Connexion à votre compte");
		System.out.println("[8] - Quitter l'application");
	}

	public static void displayBooks() {
		ArrayList<Book> books = bookJob.selectAllBooks();

		System.out.format(AppUtils.lineBook);
		System.out.format(AppUtils.headerBook);
		System.out.format(AppUtils.lineBook);
		String state = null;
		for (Book book : books) {
			state = book.isState() ? "Neuf" : "Occasion";
			System.out.format(AppUtils.formatBook, book.getId(), book.getTitle(), book.getAuthor(),
					book.getPublishYear(), book.getPrice(), state);
		}
		System.out.format(AppUtils.lineBook);
		System.out.println();
	}

	public static void displayThematics() {
		ArrayList<Thematic> thematics = bookJob.selectThematics();

		System.out.format(AppUtils.lineThematic);
		System.out.format(AppUtils.headerThematic);
		System.out.format(AppUtils.lineThematic);
		for (Thematic th : thematics) {
			System.out.format(AppUtils.formatThematic, th.getId(), th.getName());
		}
		System.out.format(AppUtils.lineThematic);
		System.out.println();
	}

	public static void displayBooksByThematic() {
		System.out.println("Saisissez l'ID du thématique que vous souhaitez : ");
		int id = input();
		Thematic thematic = bookJob.getOneThematic(id);

		if (thematic != null) {
			ArrayList<Book> books = bookJob.selectAllBooksByThematic(id);

			if (books.isEmpty()) {
				throw new RuntimeException("***Il n'y a pas (encore) de livres associés à cette thématique !\n***");
			}

			System.out.println("Thématique : " + thematic.getName());
			System.out.format(AppUtils.lineBook);
			System.out.format(AppUtils.headerBook);
			System.out.format(AppUtils.lineBook);
			String state = null;
			for (Book book : books) {
				state = book.isState() ? "Neuf" : "Occasion";
				System.out.format(AppUtils.formatBook, book.getId(), book.getTitle(), book.getAuthor(),
						book.getPublishYear(), book.getPrice(), state);
			}
			System.out.format(AppUtils.lineBook);
			System.out.println();
		} else {
			throw new RuntimeException("***Vous demandez une thématique inexistante !***");
		}

	}

	private static void welcome() {
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("|                                                                      |");
		System.out.println("|           Bonjour et bienvenue dans la librarie PH BOOKS !           |");
		System.out.println("|                                                                      |");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println();
	}

	private static String inputStr() {
		String str;
		while (scan.hasNextLine() == false)
			scan.next();
		str = scan.next();
		return str;
	}

	private static int input() {
		int choice;
		while (scan.hasNextInt() == false)
			scan.next();
		choice = scan.nextInt();
		return choice;
	}
}
