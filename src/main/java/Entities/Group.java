package Entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Group {
    private Set<User> users;
    private PurchaseList purchaseList;
    private PlanningList planningList;
    private PurchaseBalance purchaseBalance;
    private static Set<Group> groups;

    public Group(Set<User> users, PurchaseList purchaseList, PlanningList planningList){
        this.users = users;
        this.purchaseList = purchaseList;
        this.planningList = planningList;
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
}
