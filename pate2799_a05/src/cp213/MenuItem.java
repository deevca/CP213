package cp213;

import java.math.BigDecimal;

/**
 * Defines the entry and cost of a menu item. Cost is stored as a BigDecimal to
 * avoid rounding errors.
 *
 * @author your entry here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2024-03-20
 */
public class MenuItem {

	// Attributes
	private static final String itemFormat = "%-12s $%5.2f";
	private String entry = null;
	private BigDecimal cost = null;

	/**
	 * Constructor. Must set cost to 2 decimal points for calculations.
	 *
	 * @param entry Entry of the menu item.
	 * @param cost  Cost of the menu item.
	 */
	public MenuItem(final String entry, final BigDecimal cost) {

		// your code here
		this.entry = entry;
		this.cost = cost;

	}

	/**
	 * Alternate constructor. Converts a double cost to BigDecimal.
	 *
	 * @param entry Entry of the menu item.
	 * @param cost  Cost of the menu item.
	 */
	public MenuItem(final String entry, final double cost) {

		// your code here
		this.entry = entry;
		this.cost = new BigDecimal(cost);

	}

	/**
	 * entry getter
	 *
	 * @return Entry of the menu item.
	 */
	public String getEntry() {
		return this.entry;
	}

	/**
	 * cost getter
	 *
	 * @return Cost of the menu item.
	 */
	public BigDecimal getCost() {
		return this.cost;
	}

	/**
	 * Returns a MenuItem as a String in the format:
	 *
	 * <pre>
	hot dog      $ 1.25
	pizza        $10.00
	 * </pre>
	 */
	@Override
	public String toString() {

		// your code here
		String output = String.format(itemFormat, this.entry, this.cost);

		return null;
	}
}