package Data.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Users {

    STANDARD(new UserData("standard_user", "secret_sauce")),
    VISUAL(new UserData("visual_user", "secret_sauce")),
    LOCKED(new UserData("locked_out_user", "secret_sauce")),
    PROBLEM(new UserData("problem_user", "secret_sauce"));

    UserData userData;
}
