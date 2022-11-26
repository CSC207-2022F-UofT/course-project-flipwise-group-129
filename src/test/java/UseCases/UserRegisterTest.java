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

//    @BeforeEach
//    void setUp() {
//    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void executeUserRegisterSuccess() {
        try {
            UserRegisterPresenter presenter = new UserRegisterPresenter();
            UserDataAccess data = new UserDataAccess();
            UserRegisterBoundaryIn input = new UserRegister(presenter, data);
            UserRegisterController controller = new UserRegisterController(input);
            boolean result = controller.controlUseCase("not_alex.yu", "IsAFoodie3", "IsAFoodie3");
            assertTrue(result);
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }
    }

    @Test
    void executeUserRegisterPwNotMatching() {
        try {
            UserRegisterPresenter presenter = new UserRegisterPresenter();
            UserDataAccess data = new UserDataAccess();
            UserRegisterBoundaryIn input = new UserRegister(presenter, data);
            UserRegisterController controller = new UserRegisterController(input);
            boolean result = controller.controlUseCase("not_alex.yu", "IsAFoodie2", "IsAFoodie3");
            assertFalse(result);
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }
    }

    @Test
    void executeUserRegisterUsernameTaken() {
        try {
            UserRegisterPresenter presenter = new UserRegisterPresenter();
            UserDataAccess data = new UserDataAccess();
            UserRegisterBoundaryIn input = new UserRegister(presenter, data);
            UserRegisterController controller = new UserRegisterController(input);
            boolean result = controller.controlUseCase("not_alex.yu", "IsAFoodie3", "IsAFoodie3");
            assertTrue(result);
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with UserDataAccess, parsing the json");
        }
    }
}