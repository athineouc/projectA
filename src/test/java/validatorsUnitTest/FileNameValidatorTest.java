package validatorsUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.validators.EmailValidator;
import projectA.validators.FileNameValidator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FileNameValidatorTest {

    @Test
    public void validator_should_return_true() throws Exception {
        String validFileName1 = "test.csv";
        String validFileName2 = "test.test.csv";

        assertEquals( true, FileNameValidator.validate(validFileName1));
        assertEquals( true, FileNameValidator.validate(validFileName2));
    }

    @Test
    public void validator_should_return_false() throws Exception {
        String invalidFileName1 = "test.txt";
        String invalidFileName2 = "test.csv.txt";

        assertEquals( false, FileNameValidator.validate(invalidFileName1));
        assertEquals( false, FileNameValidator.validate(invalidFileName2));

    }
}
