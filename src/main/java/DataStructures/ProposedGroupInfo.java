package DataStructures;
public class ProposedGroupInfo {

    private final String name;

    private final String userId;

    public ProposedGroupInfo(String name, String userId){
        this.name = name;
        this.userId = userId;
    }

    public String getName(){
        return this.name;
    }
    public String getUserId() { return this.userId;}
}
