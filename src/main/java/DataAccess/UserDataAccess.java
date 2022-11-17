package DataAccess;
import org.*;
import java.io.FileWriter;

import DataAccessInterface.UserDataInterface;

public class UserDataAccess implements UserDataInterface {
    private final JSONObject jsonFile;
    private final map

    public void FileUserRead(String jsonPath) {
        jsonFile = new JSONObject(jsonPath);
        if (jsonFile.length() == 0) {
            this.FileUserSave();

        } else {

        }
    }

    public void FileUserSave(String jsonPath) {

    }

    private void FileUserSave() {

    }
}
