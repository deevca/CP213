package cp213;

import java.time.LocalDate;

/**
 * Tests the Student class.
 *
 * @author your name here
 * @version 2022-01-17
 */
public class Main {

	public static void main(String[] args) {
		final String line = "-".repeat(40);
		int id = 123456;
		String surname = "Brown";
		String forename = "David";
		LocalDate birthDate = LocalDate.parse("1962-10-25");
		Student student = new Student(id, surname, forename, birthDate);
		System.out.println("New Student:");
		System.out.println(student);
		System.out.println(line);
		System.out.println("Test Getters");

		// call getters here
		System.out.println("ID: " + student.getId());
		System.out.println("Surname: " + student.getSurname());
		System.out.println("Forename: " + student.getForename());
		System.out.println("Birthdate: " + student.getBirthDate());

		System.out.println(line);
		System.out.println("Test Setters");

		// call setters here
		student.setId(123457);
		student.setSurname("Brian");
		student.setForename("Danny");
		student.setBirthDate(LocalDate.parse("1963-10-25"));

		System.out.println("Updated Student:");
		System.out.println(student);
		System.out.println(line);
		System.out.println("Test compareTo");

		// create new Students - call comparisons here
		String newSurname = "Deev";
		String newForename = "Patel";
		LocalDate newBirthDate = LocalDate.parse("2004-12-18");
		int newId = 169032;
		Student student2 = new Student(newId, newSurname, newForename, newBirthDate);

		// Compare to student and student2
		int compare = student.compareTo(student2);

		System.out.println("\nNew Student:");
		System.out.println(student2);
		System.out.println("\nCompare: " + compare);
	}

}
