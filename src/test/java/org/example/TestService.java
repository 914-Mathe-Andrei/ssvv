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
	public void testSaveStudent() {
		Assert.assertEquals(1, service.saveStudent("1", "mathe", 934));
		Assert.assertEquals(0, service.saveStudent("", "mathe", 934));
		service.deleteStudent("1");
	}
}
