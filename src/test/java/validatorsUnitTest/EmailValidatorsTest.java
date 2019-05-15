package validatorsUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.validators.EmailValidator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class EmailValidatorsTest {

    @Test
    public void validator_should_return_true() throws Exception {
        String validMail1 = "tmp@gmail.com";
        String validMail2 = "tmp@gmail.projecta.com";

        assertEquals( true, EmailValidator.validate(validMail1));
        assertEquals( true, EmailValidator.validate(validMail2));
    }

    @Test
    public void validator_should_return_false() throws Exception {
        String invalidMail1 = "tmp@gmail";
        String invalidMail2 = "tmp@gma;il.com";
        String invalidMail3 = "tmp@gma\"il.com";

        assertEquals( false, EmailValidator.validate(invalidMail1));
        assertEquals( false, EmailValidator.validate(invalidMail2));
        assertEquals( false, EmailValidator.validate(invalidMail3));
    }
}
