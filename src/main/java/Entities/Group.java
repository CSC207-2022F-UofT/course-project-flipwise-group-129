package Entities;

public class Group {
    private User[] users;
    private PurchaseList purchaseList;
    private PlanningList planningList;
    private PurchaseBalance purchaseBalance;
    private static Group[] groups;

    public Group(User[] users, PurchaseList purchaseList, PlanningList planningList, Group[] groups){
        this.users = users;
        this.purchaseList = purchaseList;
        this.planningList = planningList;
        this.groups = groups;
    }

    public Group(User[] users, PurchaseList purchaseList, PlanningList planningList){
        this.users = users;
        this.purchaseList = purchaseList;
        this.planningList = planningList;
    }

    public User[] getUsers(){
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

    public Group[] getGroups(){
        return groups;
    }
}
