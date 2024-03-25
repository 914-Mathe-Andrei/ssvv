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
	public void testValidate_good() {
		Tema tema = new Tema("1", "string", 2, 1);
		validator.validate(tema);
	}

	@Test
	public void testValidate_bad() {
		Tema tema = new Tema("", "string", 2, 1);
		Exception exception = Assert.assertThrows(ValidationException.class, () -> {
			validator.validate(tema);
		});

		String expectedMessage = "ID invalid! \n";
		String actualMessage = exception.getMessage();

		Assert.assertEquals(expectedMessage, actualMessage);
	}
}
