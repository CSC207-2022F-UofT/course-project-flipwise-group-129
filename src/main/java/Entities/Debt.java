package Entities;

public class Debt {

    private User user1;
    private User user2;
    private String groupId;

    private Debt(User user1, User user2, String groupId){
        this.user1 = user1;
        this.user2 = user2;
        this.groupId = groupId;
    }
}
