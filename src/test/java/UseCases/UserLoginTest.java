package UseCases;

import Controllers.UserLoginController;
import DataAccess.GroupDataAccess;
import DataAccess.UserDataAccess;
import DataStructures.LoggedInInfo;
import Entities.User;
import InputBoundary.UserLoginBoundaryIn;
import Presenters.UserLoginPresenter;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginTest {

    @Test
    void executeUserLoginSuccess() {
        UserDataAccess userData = null;
        GroupDataAccess groupData = null;
        try {
            userData = new UserDataAccess("testusers.json");
            groupData = new GroupDataAccess("testgroups.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess or GroupDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with parsing json UserDataAccess or GroupDataAccess");
        }

        UserLoginPresenter presenter = new UserLoginPresenter();
        UserLoginBoundaryIn input = new UserLogin(presenter, userData, groupData);
        UserLoginController controller = new UserLoginController(input);
        LoggedInInfo outputInfo = controller.controlUseCase("randomC", "1111");
        assertTrue(outputInfo.statusBool());
    }

    @Test
    void executeUserLoginWrongPw() {
        UserDataAccess userData = null;
        GroupDataAccess groupData = null;
        try {
            userData = new UserDataAccess("testusers.json");
            groupData = new GroupDataAccess("testgroups.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess or GroupDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with parsing json UserDataAccess or GroupDataAccess");
        }

        UserLoginPresenter presenter = new UserLoginPresenter();
        UserLoginBoundaryIn input = new UserLogin(presenter, userData, groupData);
        UserLoginController controller = new UserLoginController(input);
        LoggedInInfo outputInfo = controller.controlUseCase("randomC", "1234");
        assertFalse(outputInfo.statusBool());
    }

    @Test
    void executeUserLoginNoUsername() {
        UserDataAccess userData = null;
        GroupDataAccess groupData = null;
        try {
            userData = new UserDataAccess("testusers.json");
            groupData = new GroupDataAccess("testgroups.json");
        } catch (IOException e) {
            fail("IOException, issue with UserDataAccess or GroupDataAccess");
        } catch (ParseException e) {
            fail("ParseException, issue with parsing json UserDataAccess or GroupDataAccess");
        }

        UserLoginPresenter presenter = new UserLoginPresenter();
        UserLoginBoundaryIn input = new UserLogin(presenter, userData, groupData);
        UserLoginController controller = new UserLoginController(input);
        LoggedInInfo outputInfo = controller.controlUseCase("possibly_alex.yu", "statS");
        assertFalse(outputInfo.statusBool());
    }
}