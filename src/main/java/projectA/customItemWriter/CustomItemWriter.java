package projectA.customItemWriter;

import projectA.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static projectA.constants.Constants.RECEIVED_EMAIL;


public class CustomItemWriter implements ItemWriter<Person> {

    private static final Logger log = LoggerFactory.getLogger(CustomItemWriter.class);

    @Override
    public void write(List<? extends Person> persons) throws Exception {

        List<Person> filteredPersons = Optional.ofNullable(persons)
                .orElseGet(() -> Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        filteredPersons.forEach(this::sendEmail);
    }

    private void sendEmail(Person person) {
        try {
            TimeUnit.SECONDS.sleep((long) 0.5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(String.format(RECEIVED_EMAIL, person.getFirstName(), person.getLastName(), person.getEmail()));
    }
}