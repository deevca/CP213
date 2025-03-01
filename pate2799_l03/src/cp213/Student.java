package cp213;

import java.time.LocalDate;

/**
 * Student class definition.
 *
 * @author Deev Patel
 * @version 2022-01-17
 */
public class Student implements Comparable<Student> {

	// Attributes
	private LocalDate birthDate = null;
	private String forename = "";
	private int id = 0;
	private String surname = "";

	/**
	 * Instantiates a Student object.
	 *
	 * @param id        student ID number
	 * @param surname   student surname
	 * @param forename  name of forename
	 * @param birthDate birthDate in 'YYYY-MM-DD' format
	 */
	public Student(int i, String s, String f, LocalDate d) {

		// assign attributes her
		forename = f;
		birthDate = d;
		id = i;
		surname = s;

		return;
	}

	/*
	 * Use attributes of forename, birthdate, id, and surname to create a formated
	 * string to display the information
	 *
	 * @return formated string of student data
	 */
	@Override
	public String toString() {
		String string = "";

		// your code here
		string = String.format("%-12s%s, %s\n%-12s%d\n%-12s%s", "Name:", surname, forename, "ID:", id, "Birthdate:",
				birthDate);
		return string;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Student target) {
		int result = 0;

		// your code here
		// compare by surname if the surnames are not same
		result = surname.compareTo(target.surname);
		if (result != 0) {
			return result;
		}

		// compare by forename if the forenames are not same
		result = forename.compareTo(target.forename);
		if (result != 0) {
			return result;
		}

		// if both surname & forename are same, compare by ids
		if (id > target.id) {
			result = 1;
		} else if (id < target.id) {
			result = -1;
		} else {
			result = 0;
		}

		return result;
	}

	// getters and setters defined here
	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public String getSurname() {
		return surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public int getId() {
		return id;
	}

}
