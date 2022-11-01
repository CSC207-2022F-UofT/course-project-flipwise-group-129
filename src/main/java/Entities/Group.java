package Entities;

public class Group {
    private String[] users;
    private PurchaseList purchaseList;
    private PlanningList planningList;
    private PurchaseBalance purchaseBalance;
    private static String[] groups;

    public Group(String[] users, PurchaseList purchaseList, PlanningList planningList, String[] groups){
        this.users = users;
        this.purchaseList = purchaseList;
        this.planningList = planningList;
        this.groups = groups;
    }

}
