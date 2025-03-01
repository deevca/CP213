package cp213;

/**
 * Inherited class in simple example of inheritance / polymorphism.
 *
 * @author David Brown
 * @version 2022-02-25
 */
public class IA extends Student {

	protected String course = null;

	public IA(final String lastName, final String firstName, final String id, final String course) {
		super(lastName, firstName, id);
		this.course = course;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString() Creates a formatted string of IA data.
	 */
	@Override
	public String toString() {
		String string = "";

		string += (super.toString() + "\nCourse: " + this.course);

		return string;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getlasttName() {
		return lastName;
	}

	public String getCourse() {
		return course;
	}

	@Override
	public String getId() {
		return id;
	}
}
