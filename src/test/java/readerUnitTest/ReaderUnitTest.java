package readerUnitTest;

import batchTestConfiguration.BatchTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import projectA.batchConfiguration.CustomBatchConfiguration;
import projectA.customItemReader.CustomItemReader;
import projectA.model.Person;

import static filesNameConstants.FilesNameConstants.*;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {CustomBatchConfiguration.class, BatchTestConfiguration.class} )
public class ReaderUnitTest {


    private CustomItemReader customItemReader = new CustomItemReader();
    private ExecutionContext executionContext = new ExecutionContext();

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;


    //Fail

    /*
     * File name :: InvalidRow1.csv
     * Context   :: {""tmp@gmail.com";"John";"Doe"}
     * */
    @Test
    public void reader_Should_Fail_1() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_1);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }

    /*
     * File name :: InvalidRow2.csv
     * Context   :: {"tmp@gm"ail.com";"John";"Doe"}
     * */
    @Test
    public void reader_Should_Fail_2() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_2);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }

    /*
     * File name :: InvalidRow3.csv
     * Context   :: {"tmp@gmail.com";"John"}
     * */
    @Test
    public void reader_Should_Fail_3() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_3);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }

    /*
     * File name :: InvalidRow4.csv
     * Context   :: {asdasda}
     * */
    @Test
    public void reader_Should_Fail_4() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_4);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }

    /*
     * File name :: InvalidRow5.csv
     * Context   :: {"tmp@gmail.com";"John";"Doe";}
     * */
    @Test
    public void reader_Should_Fail_5() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_5);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }

    /*
     * File name :: InvalidRow6.csv
     * Context   :: {"tmp@gmail.com";"John","Doe"}
     * */
    @Test
    public void reader_Should_Fail_6() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(INVALID_ROW_6);
        reader.open(executionContext);

        assertThatThrownBy(() -> reader.read())
                .isInstanceOf(FlatFileParseException.class);
    }


    //Pass


    /*
     * File name :: validRow1.csv
     * Context   :: {"tmp@gmail.com";"John";"Doe"}
     * */
    @Test
    public void reader_Should_Pass_1() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(VALID_ROW_1);
        reader.open(executionContext);
        Person person = new Person("tmp@gmail.com", "John", "Doe");

        assertEquals(person, reader.read());
    }

    /*
     * File name :: validRow2.csv
     * Context   :: {"tmp@g""mail.com";"John";"Doe"}
     * */
    @Test
    public void reader_Should_Pass_2() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(VALID_ROW_2);
        reader.open(executionContext);
        Person person = new Person("tmp@g\"mail.com", "John", "Doe");

        assertEquals(person, reader.read());
    }

    /*
     * File name :: multipleLines.csv
     * Context   :: {"tmp@gmail.com";"John";"Doe"} in 10 lines
     * */
    @Test
    public void reader_Should_Pass_multiple_lines() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(MULTIPLE_LINES);
        reader.open(executionContext);
        Person person = new Person("tmp@gmail.com", "John", "Doe");

        Person createdPerson = (Person) reader.read();
        while (createdPerson != null) {
            assertEquals(person, reader.read());
            createdPerson = (Person) reader.read();
        }
    }

    /*
     * File name :: validRow3.csv
     * Context   :: {"tmp@gma;il.com";"John";"Doe"}
     * */
    @Test
    public void reader_Should_Pass_3() throws Exception {
        final FlatFileItemReader reader = customItemReader.initializeReader(VALID_ROW_3);
        reader.open(executionContext);
        Person person = new Person("tmp@gma;il.com", "John", "Doe");
        assertEquals(person, reader.read());

    }
}