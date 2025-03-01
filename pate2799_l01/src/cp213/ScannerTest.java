package cp213;

import java.util.Scanner;

/**
 * Class to demonstrate the use of Scanner with a keyboard and File objects.
 *
 * @author Deev Patel
 * @version 2022-01-08
 */
public class ScannerTest {

	/**
	 * Count lines in the scanned file.
	 *
	 * @param source Scanner to process
	 * @return number of lines in scanned file
	 */
	public static int countLines(final Scanner source) {
		int count = 0;

		// your code here
		while (source.hasNextLine()) {
			// Move to the next line in scanner
			source.hasNext();
			count++;
		}
		return count;
	}

	/**
	 * Count tokens in the scanned object.
	 *
	 * @param source Scanner to process
	 * @return number of tokens in scanned object
	 */
	public static int countTokens(final Scanner source) {
		int tokens = 0;

		// your code here
		while (source.hasNext()) {
			// Move to next token in scaner
			source.next();
			tokens++;
		}

		return tokens;
	}

	/**
	 * Ask for and total integers from the keyboard.
	 *
	 * @param source Scanner to process
	 * @return total of positive integers entered from keyboard
	 */
	public static int readNumbers(final Scanner keyboard) {
		int total = 0;

		// your code here
		boolean valid = true;
		while (valid) {
			if (keyboard.hasNextInt()) {
				total += keyboard.nextInt();
			} else {
				if (keyboard.hasNext()) {
					continue;
				}
				final String input = keyboard.next();
				if (input.equalsIgnoreCase(input)) {
					break;
				} else {
					System.out.println("Not a valid integer");
				}
			}
		}

		return total;
	}

}
