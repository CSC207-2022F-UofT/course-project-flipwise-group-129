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

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class UserRegisterTest {
    
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

    /**
     * Test a successful user register.
     */
    @Test
    void executeUserRegisterSuccess() throws IOException {
        setUp();
        
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
        
        tearDown();
    }

    /**
     * Test an unsuccessful user register where the password and the repeat
     * password do not match.
     */
    @Test
    void executeUserRegisterPwNotMatching() throws IOException {
        setUp();
        
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
        
        tearDown();
    }

    /**
     * Test an unsuccessful user register where the username entered
     * by the user is already taken.
     */
    @Test
    void executeUserRegisterUsernameTaken() throws IOException {
        setUp();
        
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
        
        tearDown();
        }
}
