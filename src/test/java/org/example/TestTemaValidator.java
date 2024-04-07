package org.example;

import domain.Tema;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import validation.TemaValidator;
import validation.ValidationException;
import validation.Validator;

public class TestTemaValidator {
	public Validator<Tema> validator;

	@Before
	public void setUp() {
		validator = new TemaValidator();
	}

	@Test
	public void testValidate_1() {
		Tema tema = new Tema("1", "string", 2, 1);
		validator.validate(tema);
	}

	@Test
	public void testValidate_2() {
		Tema tema = new Tema("", "string", 2, 1);
		Exception exception = Assert.assertThrows(ValidationException.class, () -> {
			validator.validate(tema);
		});

		String expectedMessage = "ID invalid! \n";
		String actualMessage = exception.getMessage();

		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValidate_3() {
		Tema tema = new Tema("1", "", 2, 1);
		Exception exception = Assert.assertThrows(ValidationException.class, () -> {
			validator.validate(tema);
		});

		String expectedMessage = "Descriere invalida! \n";
		String actualMessage = exception.getMessage();

		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValidate_4() {
		Tema tema = new Tema("1", "string", 0, 1);
		Exception exception = Assert.assertThrows(ValidationException.class, () -> {
			validator.validate(tema);
		});

		String expectedMessage = "Deadline invalid! \n";
		String actualMessage = exception.getMessage();

		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testValidate_5() {
		Tema tema = new Tema("1", "string", 2, 0);
		Exception exception = Assert.assertThrows(ValidationException.class, () -> {
			validator.validate(tema);
		});

		String expectedMessage = "Data de primire invalida! \n";
		String actualMessage = exception.getMessage();

		Assert.assertEquals(expectedMessage, actualMessage);
	}
}
