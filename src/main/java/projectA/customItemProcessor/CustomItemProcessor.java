package projectA.customItemProcessor;

import projectA.model.Person;
import projectA.validators.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import static projectA.constants.Constants.INVALID_EMAIL_ERROR;

public class CustomItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(CustomItemProcessor.class);


    @Override
    public Person process(final Person person) throws Exception {
        if (EmailValidator.validate(person.getEmail())) {
            return person;
        }
        log.info(String.format(INVALID_EMAIL_ERROR, person.getFirstName(), person.getLastName(), person.getEmail()));
        return null;
    }

}
