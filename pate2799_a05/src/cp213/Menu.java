package cp213;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-03-20
 */
public class Menu {

	// Attributes.
	// define a List of MenuItem objects
	// Note that this must be a *List* of some flavour
	// @See
	// https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html

	// your code here
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

	/**
	 * Creates a new Menu from an existing List of MenuItems. MenuItems are copied
	 * into the Menu List.
	 *
	 * @param items an existing List of MenuItems.
	 */
	public Menu(List<MenuItem> items) {

		// your code here
		menuList = new ArrayList<MenuItem>(items);
	}

	/**
	 * Constructor from a Scanner of MenuItem strings. Each line in the Scanner
	 * corresponds to a MenuItem. You have to read the Scanner line by line and add
	 * each MenuItem to the List of items.
	 *
	 * @param fileScanner A Scanner accessing MenuItem String data.
	 */
	public Menu(Scanner fileScanner) {

		// your code here
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			// Space between split
			String[] parts = line.split(" ", 2);

			if (parts.length >= 2) {
				String num = parts[0];
				double cost = Double.parseDouble(num);
				String entry = parts[1].trim();
				MenuItem item = new MenuItem(entry, cost);
				menuList.add(item);
			}
		}

	}

	/**
	 * Returns the List's i-th MenuItem.
	 *
	 * @param i Index of a MenuItem.
	 * @return the MenuItem at index i
	 */
	public MenuItem getItem(int i) {

		// your code here
		return new MenuItem(menuList.get(i).getEntry(), menuList.get(i).getCost());

	}

	/**
	 * Returns the number of MenuItems in the items List.
	 *
	 * @return Size of the items List.
	 */
	public int size() {

		// your code here

		return menuList.size();
	}

	/**
	 * Returns the Menu items as a String in the format:
	 *
	 * <pre>
	5) poutine      $ 3.75
	6) pizza        $10.00
	 * </pre>
	 *
	 * where n) is the index + 1 of the MenuItems in the List.
	 */
	@Override
	public String toString() {

		// your code here
		String menu = "";
		String format = "%-12s $%5.2f";
		int i = 0;

		while (i < size()) {
			String output = "";

			output = String.format(format, menuList.get(i).getEntry(), menuList.get(i).getCost());
			i += 1;
			menu += " " + i + ") " + output + '\n';
		}

		return menu;
	}
}