package org.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import domain.Nota;
import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class TestService {
	private Service service;

	@Before
	public void setUp() {
		Validator<Student> studentValidator = new StudentValidator();
		Validator<Tema> temaValidator = new TemaValidator();
		Validator<Nota> notaValidator = new NotaValidator();

		StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
		TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
		NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

		service = new Service(fileRepository1, fileRepository2, fileRepository3);
	}

	@Test
	public void testSaveStudent_1() {
		Assert.assertEquals(1, service.saveStudent("5", "mathe", 934));
		service.deleteStudent("5");
	}

	@Test
	public void testSaveStudent_2() {
		Assert.assertEquals(0, service.saveStudent("", "mathe", 934));
	}

	@Test
	public void testSaveStudent_3() {
		Assert.assertEquals(1, service.saveStudent("1", "b", 934));
		service.deleteStudent("1");
	}

	@Test
	public void testSaveStudent_4() {
		Assert.assertEquals(0, service.saveStudent("1", "", 934));
	}

	@Test
	public void testSaveStudent_5() {
		Assert.assertEquals(0, service.saveStudent("1", "mathe", 110));
		service.deleteStudent("1");
	}

	@Test
	public void testSaveStudent_6() {
		Assert.assertEquals(1, service.saveStudent("1", "mathe", 111));
	}

	@Test
	public void testSaveStudent_7() {
		Assert.assertEquals(1, service.saveStudent("1", "mathe", 937));
		service.deleteStudent("1");
	}

	@Test
	public void testSaveStudent_8() {
		Assert.assertEquals(0, service.saveStudent("1", "mathe", 938));
	}
}
