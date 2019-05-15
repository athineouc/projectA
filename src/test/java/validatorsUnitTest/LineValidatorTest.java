package validatorsUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.validators.LineValidator;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class LineValidatorTest {

    @Test
    public void validator_should_return_true_to_skip_line() throws Exception {
        LineValidator lineValidator = new LineValidator();

        assertEquals( true, lineValidator.shouldSkip(new FlatFileParseException("",""),1));

    }

    @Test
    public void validator_should_return_false_NOT_skip_line() throws Exception {
        LineValidator lineValidator = new LineValidator();

        assertEquals( false, lineValidator.shouldSkip(null ,1));

    }
}
