package onlineexamsystem;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static Map<String, String> users = new HashMap<>();

    static {
        // Static initializer block for predefined users
    	users.put("user", "pass");
        users.put("mayank", "59");
        users.put("anshul", "28");
        users.put("manu", "58");
    }

    public static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
