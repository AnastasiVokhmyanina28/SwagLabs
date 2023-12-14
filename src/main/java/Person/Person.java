package Person;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@Accessors(chain = true)
public class Person {
    public String name;
    public String lastName;
    public String postalCode;

    public static Person randomized() {
        return new Person()
                .setName(RandomStringUtils.random(10, true, false))
                .setLastName(RandomStringUtils.random(10, true, false))
                .setPostalCode(RandomStringUtils.random(6, false, true));
    }
}
