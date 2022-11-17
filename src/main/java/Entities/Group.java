package Entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Group {
    private Set<User> users;
    private PurchaseList purchaseList;
    private PlanningList planningList;
    private PurchaseBalance purchaseBalance;

    private String groupName;

    private String groupId;
    private static Set<Group> groups;

    public Group(String name, Set<User> users){
        // this.groupId = will be implemented ones daatstore has been
        this.groupName = name;
        this.users = users;
        this.purchaseList = new PurchaseList();
        this.planningList = new PlanningList();
        groups.add(this);
    }

    public Set<User> getUsers(){
        return this.users;
    }

    public PurchaseList getPurchaseList(){
        return this.purchaseList;
    }

    public PlanningList getPlanningList() {
        return planningList;
    }

    public PurchaseBalance getPurchaseBalance() {
        return purchaseBalance;
    }

    public static Set<Group> getGroups(){
        return groups;
    }

    public void addUser(User user){
        this.users.add(user);
    }
}
