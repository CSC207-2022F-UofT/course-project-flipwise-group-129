package Entities;
import org.json.simple.
public class Debt {

    private User userOwed;
    private User userOwing;
    private String groupId;
    private Double debtValue;

    private Debt(User user1, User user2, String groupId){
        this.userOwed = user1;
        this.userOwing = user2;
        this.groupId = groupId;
        this.debtValue = 0.0;
    }

    private Debt(String jsonDebtString){

    }

    public User getUserOwed(){
        return this.userOwed;
    }

    public User getUserOwing() {
        return this.userOwing;
    }

    public User[] getUsers(){
        User[] users = new User[2];
        users[0] = this.userOwed;
        users[1] = this.userOwing;
        return users;
    }

    public Double getDebtValue(){
        return this.debtValue;
    }

    public void setDebtValue(Double debtValue){
        this.debtValue = debtValue;
    }

    public String getGroupId() {
        return this.groupId;
    }
}
