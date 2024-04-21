package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import repository.*;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class ServiceBigBangTest extends TestCase {
	private Service service;

	public void setUp() {
		Validator<Student> studentValidator = new StudentValidator();
		Validator<Tema> temaValidator = new TemaValidator();
		Validator<Nota> notaValidator = new NotaValidator();

		StudentRepository repo1 = new StudentRepository(studentValidator);
		TemaRepository repo2 = new TemaRepository(temaValidator);
		NotaRepository repo3 = new NotaRepository(notaValidator);

		service = new Service(repo1, repo2, repo3);
	}

	public void testSaveStudent() {
		int result = service.saveStudent("1", "bla", 111);
		assertEquals(result, 1);

		result = service.saveStudent(null, "bla", 111);
		assertEquals(result, 0);

		result = service.saveStudent("1", null, 111);
		assertEquals(result, 0);

		result = service.saveStudent("1", "bla", 1);
		assertEquals(result, 0);
	}

	public void testSaveTema() {
		int result = service.saveTema("1", "fjfkdkd", 2, 2);
		assertEquals(result, 1);

		result = service.saveTema(null, "fjfkdkd", 2, 2);
		assertEquals(result, 0);

		result = service.saveTema("1", null, 2, 2);
		assertEquals(result, 0);

		result = service.saveTema("1", "fjfkdkd", 3, 6);
		assertEquals(result, 0);

		result = service.saveTema("1", "fjfkdkd", 2, 0);
		assertEquals(result, 0);

		result = service.saveTema("1", "fjfkdkd", 15, 2);
		assertEquals(result, 0);
	}

	public void testSaveNota() {
		int result = service.saveNota("1", "1", 3, 3, "ddhhf");
		assertEquals(result, -1);

		result = service.saveStudent("1", "bla", 111);
		assertEquals(result, 1);
		result = service.saveTema("1", "fjfkdkd", 2, 2);
		assertEquals(result, 1);

		result = service.saveNota("1", "1", 3, 3, "ddhhf");
		assertEquals(result, 1);

		result = service.saveNota("1", "1", 3, -1, "ddhhf");
		assertEquals(result, 0);

		result = service.saveNota("1", "1", 11, 2, "ddhhf");
		assertEquals(result, 0);
	}
}