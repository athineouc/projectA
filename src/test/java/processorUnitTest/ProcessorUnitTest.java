package processorUnitTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.customItemProcessor.CustomItemProcessor;
import projectA.model.Person;
import projectA.validators.LineValidator;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
public class ProcessorUnitTest {

    private CustomItemProcessor customItemProcessor = new CustomItemProcessor();

    @Test
    public void validator_should_return_Person() throws Exception {

        Person person = new Person("tmp@gmail.com","Chris","Athinaiou");

        assertEquals( person, customItemProcessor.process(person));

    }

    @Test
    public void validator_should_return_null() throws Exception {

        Person person = new Person("tmp@gmail","Chris","Athinaiou");

        assertEquals( null, customItemProcessor.process(person));

    }
}
