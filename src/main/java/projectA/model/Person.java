package projectA.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Person {
    private String email;
    private String firstName;
    private String lastName;

}
