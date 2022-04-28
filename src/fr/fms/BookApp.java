package fr.fms;

import java.util.ArrayList;
import fr.fms.business.IBusinessBookImpl;
import fr.fms.entities.Book;
import fr.fms.entities.Customer;
import fr.fms.entities.Thematic;

/**
 * Application de console d'achat de livres L'utilisateur peut ajouter et
 * supprimer des livres du panier ainsi que passer commande à tout moment Pour
 * passer commande, l'utilisateur doit être connecté, s'il n'a pas de compte, il
 * a la possibilité d'en créer un
 * 
 * @author Delmerie JOHN ROSE
 *
 */
public class BookApp {

	private static IBusinessBookImpl bookJob = new IBusinessBookImpl();
	private static int customerId = 0;
	private static String login = null;

	public static void main(String[] args) {
		welcome();
		int choice = 0;
		while (choice != 8) {
			try {
				displayMenu();
				switch (choice = AppUtils.input()) {
				case 1:
					addBook();
					break;
				case 2:
					removeBook();
					break;
				case 3:
					displayCart(true);
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
					login();
					break;
				case 8:
					System.out.println("Bye, à bientôt");
					break;
				default:
					System.out.println("Mauvaise saisie, recommencez !");
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("-------------------------------------------------");
				System.out.println(e.getMessage());
				System.out.println("-------------------------------------------------");
				System.out.println();
			}
		}
	}

	/**
	 * Méthode qui affiche un message de bienvenue à l'application
	 */
	private static void welcome() {
		System.out.println(AppUtils.formatMenuWelcome);
		System.out.println("|                                                                      |");
		System.out.println("|           Bonjour et bienvenue dans la librarie PH BOOKS !           |");
		System.out.println("|                                                                      |");
		System.out.println(AppUtils.formatMenuWelcome);
		System.out.println();
	}

	/**
	 * Méthode qui permet d'afficher le menu principal
	 */
	public static void displayMenu() {
		if (login != null) {
			System.out.println();
			System.out.println("Compte de " + login);
		}

		System.out.println(AppUtils.formatMenuWelcome);
		System.out.println("|                            Menu principal                            |");
		System.out.println("|      Que souhaitez-vous faire ? [Saisir le chiffre correspondant]    |");
		System.out.println(AppUtils.formatMenuWelcome);
		System.out.println("[1] - Ajouter un livre au panier");
		System.out.println("[2] - Supprimer un livre du panier");
		System.out.println("[3] - Afficher le panier et passer commande");
		System.out.println("[4] - Afficher tous les livres de la librarie");
		System.out.println("[5] - Afficher toutes les thématiques");
		System.out.println("[6] - Afficher les articles par thématique");
		System.out.println("[7] - Connexion à votre compte");
		System.out.println("[8] - Quitter l'application");
	}

	/**
	 * Méthode qui permet à l'utilisateur d'ajouter un livre au panier
	 */
	public static void addBook() {
		displayBooks();
		System.out.println("Saisissez l'ID du livre à ajouter au panier");
		int id = AppUtils.input();
		Book book = bookJob.getOneBook(id);

		if (book != null) {
			bookJob.addToCart(book);
			displayCart(false);
		} else {
			throw new RuntimeException("Vous demandez un livre inexistant !");
		}
	}

	/**
	 * Méthode qui permet à l'utilisateur de supprimer un livre du panier
	 */
	public static void removeBook() {
		if (bookJob.isCartEmpty()) {
			throw new RuntimeException("Votre panier est vide !");
		}

		System.out.println("Saisissez l'ID du livre à supprimer au panier");
		if (bookJob.removeFromCart(AppUtils.input())) {
			System.out.println("Le livre a bien été supprimé du panier");
			displayCart(false);
		} else {
			throw new RuntimeException("Ce livre ne figure pas dans votre panier !");
		}
	}

	/**
	 * Méthode qui permet d'afficher la liste de l'ensemble des livres de la
	 * boutique
	 */
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

	/**
	 * Méthode qui permet d'afficher la liste des thématiques
	 */
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

	/**
	 * Méthode qui permet d'afficher la liste des livres selon une thématique
	 * choisie par l'utilisateur
	 */
	public static void displayBooksByThematic() {
		System.out.println("Saisissez l'ID du thématique que vous souhaitez : ");
		int id = AppUtils.input();
		Thematic thematic = bookJob.getOneThematic(id);

		if (thematic != null) {
			ArrayList<Book> books = bookJob.selectAllBooksByThematic(id);

			if (books.isEmpty()) {
				throw new RuntimeException("Il n'y a pas (encore) de livres associés à cette thématique !");
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
			throw new RuntimeException("Vous demandez une thématique inexistante !");
		}
	}

	/**
	 * Méthode qui permet d'afficher le panier Affiche le total selon le flag
	 * 
	 * @param flag
	 */
	public static void displayCart(boolean flag) {
		if (bookJob.isCartEmpty()) {
			throw new RuntimeException("Votre panier est vide !");
		}

		System.out.format(AppUtils.lineCart);
		System.out.format(AppUtils.headerCart);
		System.out.format(AppUtils.lineCart);
		for (Book book : bookJob.getCart()) {
			double qty = book.getPrice() * book.getQuantity();
			System.out.format(AppUtils.formatCart, book.getId(),
					book.getTitle() + " - " + book.getAuthor() + " - " + book.getPublishYear(), book.getPrice(),
					book.getQuantity(), qty);
		}
		System.out.format(AppUtils.lineCart);
		if (flag) {
			System.out.format(AppUtils.footerCart, "PRIX TOTAL DE VOTRE PANIER", bookJob.getTotal());
			System.out.format(AppUtils.lineCart);
			System.out.println();
			validateOrder();
		}
	}

	/**
	 * Méthode qui permet de valider une commande Propose à l'utilisateur de se
	 * connecter ou de se créer un compte pour passer commande
	 * 
	 */
	public static void validateOrder() {
		System.out.println("Souhaitez-vous passer commande ? [Oui/Non] ");

		if (AppUtils.inputStr().equalsIgnoreCase("oui")) {

			// si un login n'existe pas, on propose de se connecter ou de se créer un compte
			if (login == null) {
				System.out.println("Vous devez avoir un compte pour continuer !");
				System.out.println("[1] - Se connecter");
				System.out.println("[2] - S'inscrire");

				switch (AppUtils.input()) {
				case 1:
					login();
					break;
				case 2:
					signin();
					break;
				default:
					break;
				}
			} else { // si le login existe, la commade se poursuit
				if (bookJob.order(customerId)) {
					System.out.println("+------------------------------------+");
					System.out.println("| Votre commande a bien été validée. |");
					System.out.println("+------------------------------------+");
					bookJob.clearCart();
				} else {
					System.out.println("Une erreur s'est produite au moment de passer la commande.");
				}
			}
		}
	}

	/**
	 * Méthode qui permet à l'utisateur de se connecter
	 */
	public static void login() {
		if (login != null) {
			System.out.println("+---------------------------+");
			System.out.println("| Vous êtes déjà connecté ! |");
			System.out.println("+---------------------------+");
		} else {
			System.out.println("Saississez votre email");
			String email = AppUtils.inputStr();
			System.out.println("Saississez votre mot de passe");
			String password = AppUtils.inputStr();

			Customer customer = bookJob.existCustomer(email, password);

			if (customer != null) {
				String fistnameString = customer.getFirstname();
				login = fistnameString.substring(0, 1).toUpperCase() + fistnameString.substring(1) + " "
						+ customer.getLastname().toUpperCase();
				customerId = customer.getId();
			} else {
				signin();
			}
		}
	}

	/**
	 * Méthode qui permet à l'utilisateur de se créer un compte
	 */
	public static void signin() {
		System.out.println("Saississez les informations suivantes pour créer votre compte :");
		System.out.println("Prénom");
		String firstname = AppUtils.inputStr();
		System.out.println("Nom");
		String lastname = AppUtils.inputStr();
		System.out.println("Email");
		String email = AppUtils.inputStr();
		System.out.println("Mot de passe");
		String password = AppUtils.inputStr();

		Customer newCustomer = new Customer(email, password, firstname, lastname);

		if (bookJob.createCustomerAccount(newCustomer)) {
			System.out.println("+-------------------------------+");
			System.out.println("| Votre compte a bien été crée. |");
			System.out.println("+-------------------------------+");
			login();
		}
	}
}
