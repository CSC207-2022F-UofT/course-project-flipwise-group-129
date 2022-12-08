/**
 * Use case for login a user who already has an account.
 */
package UseCaseLayer.UseCases;
import UseCaseLayer.DataAccessInterface.GroupDataInterface;
import UseCaseLayer.DataAccessInterface.UserDataInterface;
import UseCaseLayer.DataStructures.LoggedInInfo;
import UseCaseLayer.DataStructures.LoginCredentials;
import Entities.*;
import UseCaseLayer.InputBoundary.UserLoginBoundaryIn;
import UseCaseLayer.OutputBoundary.UserLoginBoundaryOut;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserLogin implements UserLoginBoundaryIn {
    final UserLoginBoundaryOut outputBoundary;
    final UserDataInterface userDataInterface;
    final GroupDataInterface groupDataInterface;

    /**
     * @param outputBoundary output boundary to display result of login
     * @param userDataAccess data access layer allowing query of user database
     * @param groupDataAccess data access layer allowing query of group database
     */
    public UserLogin(UserLoginBoundaryOut outputBoundary, UserDataInterface userDataAccess, GroupDataInterface groupDataAccess) {
        this.outputBoundary = outputBoundary;
        this.userDataInterface = userDataAccess;
        this.groupDataInterface = groupDataAccess;
    }

    /**
     * Updates the outputBoundary as to the success for failure of a login attempt.
     *
     * @param credentials the login credentials that the user entered
     * @return the info to display after the login
     */
    @Override
    public LoggedInInfo executeUserLogin(LoginCredentials credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        return(outputBoundary.successfulLogin(usernamePasswordMatch(username, password)));
    }

    /**
     * Helper method to determine if the user exists and if the passwords match.
     *
     * @param username username entered by the user
     * @param password password entered by the user
     * @return if the login details are valid
     */
    private LoggedInInfo usernamePasswordMatch(String username, String password) {
        try {
            if (userDataInterface.userIdExists(username)) {
                String userDetails = userDataInterface.userAsString(username);
                User user = User.fromString(userDetails);
                if (Objects.equals(user.getPassword(), password)) {
                    return(successDetails(user));
                }
            }
        } catch (IOException | ParseException e) {
            return new LoggedInInfo(false);
        }
        return new LoggedInInfo(false);
    }

    /**
     * Creates the data structure that reports to outcome of a login
     *
     * @param user user object that logged in successfully
     * @return A LoggedInInfo data structure
     */
    private LoggedInInfo successDetails(User user) {
        List<List<Object>> allGroups = new ArrayList<>();
        List<String> groups = user.getGroups();
        for (String stringGroup : groups) {
            // Gets a group representation of each group
            Group group;
            try {
                group = Group.fromString(groupDataInterface.groupAsString(stringGroup));
            } catch (IOException | ParseException e) {
                return new LoggedInInfo(false);
            }
            List<Object> eachGroup = new ArrayList<>();

            eachGroup.add(group.getGroupId());
            eachGroup.add(group.getGroupName());

            eachGroup.add(getPlanning(group.getPlanningList()));
            eachGroup.add(getPurchase(group.getPurchaseList()));

            eachGroup.add(getUsersAsList(group));

            List<Debt> debtAsDebts = group.getPurchaseBalance().getAllDebts();
            eachGroup.add(getDebtAsString(debtAsDebts));

            // Add all the group details to outer list.
            allGroups.add(eachGroup);
        }
        return new LoggedInInfo(user.getUsername(), allGroups);
    }

    /**
     * Converts debt from a debt objects to a Nested List of String, which is displayable by the view.
     * Helper method for successDetails.
     *
     * @param debtAsDebts list of all debts in a group
     * @return the nested list representation of the debt
     */
    private List<List<String>> getDebtAsString(List<Debt> debtAsDebts) {
        List<List<String>> debtAsString = new ArrayList<>();
        for (Debt debt : debtAsDebts) {
            ArrayList<String> debtInfo = new ArrayList<>();
            debtInfo.add(debt.getUserOwed().getUsername());
            debtInfo.add(debt.getUserOwing().getUsername());
            debtInfo.add(debt.getDebtValue().toString());
            debtAsString.add(debtInfo);
        }
        return(debtAsString);
    }

    /**
     * Returns a string of all the usernames in a group as a list of strings.
     * Helper method for successDetails.
     *
     * @param group the group to get all users fomr
     * @return a list of string of usernames
     */
    private List<String> getUsersAsList(Group group) {
        return new ArrayList<>(group.getUsers());
    }

    /**
     * Returns a nested list representation of the planning list of a group.
     * Helper method for successDetails.
     *
     * @param planningList a group's planning list
     * @return a list representation of a planning list
     */
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

    /**
     * Returns a nested list representation of the purchase list of a group.
     * Helper method for successDetails.
     *
     * @param purchaseList purchase list for the group
     * @return a nested list representation of the purchase list
     */
    private List<List<String>> getPurchase(PurchaseList purchaseList){
        List<List<String>> stringPurchasedList = new ArrayList<>();
        for(Item curItem: purchaseList.getItems()){
            List<String> currentItem = new ArrayList<>();
            currentItem.add(curItem.getItemId());
            currentItem.add(curItem.getItemName());
            currentItem.add(String.valueOf(curItem.getPrice()));
            currentItem.add(curItem.getBuyer());
            stringPurchasedList.add(currentItem);
        }
        return stringPurchasedList;
    }

}
