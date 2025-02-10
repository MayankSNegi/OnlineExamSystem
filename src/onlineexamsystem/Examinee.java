package onlineexamsystem;

public class Examinee extends User {
    
    public Examinee(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Examinee";
    }
}