package fr.fms;

import java.util.Scanner;

public class AppUtils {
	public static String formatBook = "| %-4d | %-50s | %-35s| %-5s | %-5s    | %-10s | %n";
	public static String lineBook =   "+------+----------------------------------------------------+------------------------------------+-------+----------+------------+%n";
	public static String headerBook = "| ID   | LIVRE                                              | AUTEUR                             | ANNEE | PRIX     |            |%n";
	
	public static String formatThematic = "| %-4d | %-19s |%n";
	public static String lineThematic =   "+------+---------------------+%n";
	public static String headerThematic = "| ID   | THEMATIQUE          |%n";
	
	public static String formatCart = "| %-4d | %-46s | %-4s | %-8s | %-6s | %n";
	public static String lineCart =   "+------+------------------------------------------------+------+----------+--------+%n";
	public static String headerCart = "| ID   | DETAILS                                        | P U  | QUANTITY | TOTAL  |%n";
	public static String footerCart = "| %-46s                          | %-6s | %n";
	
	public static String formatMenuWelcome = "+----------------------------------------------------------------------+";
	
	public static Scanner scan = new Scanner(System.in);
	
	/*
	 * Méthode qui retourne une chaine de caracètre ca saisie au scanner
	 */
	public static String inputStr() {
		String str;
		while (scan.hasNextLine() == false)
			scan.next();
		str = scan.next();
		return str;
	}

	/**
	 * Méthode qui retourne un entier saisi au scanner
	 * 
	 * @return
	 */
	public static int input() {
		int choice;
		while (scan.hasNextInt() == false)
			scan.next();
		choice = scan.nextInt();
		return choice;
	}
}
