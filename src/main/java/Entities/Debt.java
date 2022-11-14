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

    public User getUser1(){
        return this.user1;
    }

    public User getUser2() {
        return this.user2;
    }

    public User[] getUsers(){
        User[] users = new User[2];
        users[0] = this.user1;
        users[1] = this.user2;
        return users;
    }

    public String getGroupId() {
        return this.groupId;
    }
}
