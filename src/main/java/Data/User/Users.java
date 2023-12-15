package Data.User;

import Data.User.UserData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Users {

    STANDARD(new UserData("standard_user", "secret_sauce")),
    VISUAL(new UserData("visual_user", "secret_sauce"));

    UserData userData;
}
