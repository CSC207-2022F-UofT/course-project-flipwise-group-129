package UseCases;

import Controllers.UserRegisterController;
import DataAccess.UserDataAccess;
import DataStructures.RegisterCredentials;
import InputBoundary.UserRegisterBoundaryIn;
import Presenters.UserRegisterPresenter;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {

    /**
     * Test a successful user register.
     */
    @Test
    void executeUserRegisterSuccess() {
        UserDataAccess data = null;
        try {
            data = new UserDataAccess("testusers.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }

        UserRegisterPresenter presenter = new UserRegisterPresenter();
        UserRegisterBoundaryIn input = new UserRegister(presenter, data);
        UserRegisterController controller = new UserRegisterController(input);
        boolean result = controller.controlUseCase("not_alex.yu", "IsAFoodie3", "IsAFoodie3");
        assertTrue(result);
    }

    /**
     * Test an unsuccessful user register where the password and the repeat
     * password do not match.
     */
    @Test
    void executeUserRegisterPwNotMatching() {
        UserDataAccess data = null;
        try {
            data = new UserDataAccess("testusers.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }

        UserRegisterPresenter presenter = new UserRegisterPresenter();
        UserRegisterBoundaryIn input = new UserRegister(presenter, data);
        UserRegisterController controller = new UserRegisterController(input);
        boolean result = controller.controlUseCase("not_alex.yu", "IsAFoodie3", "IsAFoodie3");
        assertFalse(result);
    }

    /**
     * Test an unsuccessful user register where the username entered
     * by the user is already taken.
     */
    @Test
    void executeUserRegisterUsernameTaken() {
        UserDataAccess data = null;
        try {
            data = new UserDataAccess("testusers.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }
        UserRegisterPresenter presenter = new UserRegisterPresenter();
        UserRegisterBoundaryIn input = new UserRegister(presenter, data);
        UserRegisterController controller = new UserRegisterController(input);
        boolean result = controller.controlUseCase("randomC", "statistical_satire", "statistical_satire");
        assertFalse(result);
        }
}
