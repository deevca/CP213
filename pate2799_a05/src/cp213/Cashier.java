package cp213;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-03-20
 */
public class Cashier {

	// Attributes
	private Menu menu = null;

	/**
	 * Constructor.
	 *
	 * @param menu A Menu.
	 */
	public Cashier(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Prints the menu.
	 */
	private void printCommands() {
		System.out.println("\nMenu:");
		System.out.println(menu.toString());
		System.out.println("Press 0 when done.");
		System.out.println("Press any other key to see the menu again.\n");
	}

	/**
	 * Asks for commands and quantities. Prints a receipt when all orders have been
	 * placed.
	 *
	 * @return the completed Order.
	 */
	public Order takeOrder() {
		System.out.println("Welcome to WLU Foodorama!");

		// your code here
		printCommands();

		Scanner customer = new Scanner(System.in);
		int selection;
		Order order = new Order();

		boolean proceed = true;

		while (proceed) {
			System.out.print("Command: ");

			try {
				selection = customer.nextInt();

				if (selection == 0) {
					proceed = false;
				}

				else {
					if (selection <= menu.size() && selection > 0) {

						int item = selection - 1;
						System.out.print("How many do you want? ");

						try {

							selection = customer.nextInt();
							int quantity = selection;

							if (selection > 0) {
								order.add(menu.getItem(item), quantity);
							}
						}

						catch (InputMismatchException e) {
							System.out.println("Not a valid number");

							printCommands();
							customer.next();
						}
					}

					else {
						printCommands();
					}
				}
			}

			catch (InputMismatchException e) {
				System.out.println("Not a valid number");

				printCommands();
				customer.next();
			}

		}
		System.out.println("----------------------------------------");
		System.out.println("Receipt of your order");
		System.out.println(order);

		return order;
	}
}