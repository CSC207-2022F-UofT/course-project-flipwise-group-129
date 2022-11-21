/**
 * Use case for login a user who already has an account
 */
package UseCases;
import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.LoggedInInfo;
import DataStructures.LoginCredentials;
import Entities.*;
import InputBoundary.UserLoginBoundaryIn;
import OutputBoundary.UserLoginBoundaryOut;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserLogin implements UserLoginBoundaryIn {

    UserLoginBoundaryOut outputBoundary;
    UserDataInterface userDataInterface;
    GroupDataInterface groupDataInterface;

    /**
     * @param outputBoundary output boundary to display result of login
     * @param userDataAccess data access layer allowing query of user database
     * @param groupDataAccess data access layer allowing query of group database
     */
    public UserLogin(UserLoginBoundaryOut outputBoundary, UserDataInterface userDataAccess, GroupDataInterface groupDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataInterface = userDataAccess;
        this.group
    }

    /**
     * Updates the outputBoundary as to the success for failure of a login attempt.
     *
     * @param credentials the login credentials that the user entered
     * @throws JsonProcessingException if there is an error parsing the JSON
     */
    @Override
    public void executeUserLogin(LoginCredentials credentials) throws JsonProcessingException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        outputBoundary.success(usernamePasswordMatch(username, password));
    }

    /**
     * Helper method to determine if the user exists and if the passwords match.
     *
     * @param username username entered by the user
     * @param password password entered by the user
     * @return if the login details are valid
     * @throws JsonProcessingException if there is an error parsing the JSON
     */
    private LoggedInInfo usernamePasswordMatch(String username, String password) throws JsonProcessingException {
        if (userDataInterface.userIdExists(username)) {
            String userDetails = userDataInterface.userAsString(username);
            User user = User.fromString(userDetails);
            if (Objects.equals(user.getPassword(), password)) {
                LoggedInInfo info = successDetails(user);
                // TODO: figure out how to pass the data up into output boundary
            }
        }
        return new LoggedInInfo("false");
    }

    /**
     * Creates the data structure that reports to outcome of a login
     *
     * @param user user object that logged in successfully
     * @return A LoggedInInfo data structure
     */
    private LoggedInInfo successDetails(User user) {
        // TODO: get the required data for LoggedInInfo
        // Get each group
        // Second layer: groupID, group name, purchase list, planning list
        //
        for (group : in )
            Group obj <- group.fromstring
        }
    }

    private List<List<String>> getPlanning(PlanningList planningList){
        List<List<String>> stringPlanningList = new ArrayList<>();
        for(Item curItem: planningList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            stringPlanningList.add(currentItem);
        }
        return stringPlanningList;
    }

    private List<List<String>> getUpdatedPurchase(PurchaseList purchaseList){
        List<List<String>> stringPurchasedList = new ArrayList<>();
        for(Item curItem: purchaseList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            currentItem.add(curItem.getBuyer().getUsername());
            currentItem.add(curItem.getPrice().toString());
            stringPurchasedList.add(currentItem);
        }
        return stringPurchasedList;
    }

}
