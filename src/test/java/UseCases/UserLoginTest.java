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
    
    @Before
    public void setUp() throws IOException {
        //copy and create duplicate test stuff
        Path copiedGroups = Paths.get("src/test/resources/testgroupsCopy.json");
        Path originalPathGroups = Paths.get("src/test/resources/testgroups.json");
        Files.copy(originalPathGroups, copiedGroups, StandardCopyOption.REPLACE_EXISTING);

        Path copiedUsers = Paths.get("src/test/resources/testusersCopy.json");
        Path originalPathUsers = Paths.get("src/test/resources/testusers.json");
        Files.copy(originalPathUsers, copiedUsers, StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDown(){
        File groupFile = new File("src/test/resources/testgroupsCopy.json");
        groupFile.delete();

        File userFile = new File("src/test/resources/testusersCopy.json");
        userFile.delete();
    }

    @Test
    void executeUserLoginSuccess() {
        setUp();
        
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
        
        tearDown();
    }

    @Test
    void executeUserLoginWrongPw() {
        setup();
        
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
        
        tearDown();
    }

    @Test
    void executeUserLoginNoUsername() {
        setUp();
        
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
        
        tearDown();
    }
}
