package projectA.customItemReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import projectA.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import projectA.validators.EmailValidator;
import projectA.validators.FileNameValidator;

import static projectA.constants.Constants.*;


public class CustomItemReader {


    private static final Logger log = LoggerFactory.getLogger(EmailValidator.class);


    public FlatFileItemReader<Person> initializeReader(String fileName) {
        if(fileName == null){
            log.error(THE_FILENAME_CANNOT_BE_NULL);
            System.exit(-1);
        }
        else if(!FileNameValidator.validate(fileName)){
            log.error(INVALID_FILE_NAME);
            System.exit(-1);
        }
        return new FlatFileItemReaderBuilder<Person>()
                .name(CUSTOM_ITEM_READER)
                .resource(new FileSystemResource(fileName))
                .strict(false)
                .delimited()
                .delimiter(";")
                .names(new String[]{EMAIL, FIRST_NAME, LAST_NAME})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();
    }
}
