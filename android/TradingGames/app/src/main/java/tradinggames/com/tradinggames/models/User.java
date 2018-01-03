package tradinggames.com.tradinggames.models;

/**
 * Created by joao on 25/10/2017.
 *
 * This is a POJO for the user
 */

public class User {

    private String userId;
    private String userName;
    private String userSecondName;
    private String userEmail;
    private String userPassword;

    public User(){

    }

    public User(User u) {
        this.userId = u.userId;
        this.userName = u.userName;
        this.userSecondName = u.userSecondName;
        this.userEmail = u.userEmail;
        this.userPassword = u.userPassword;
    }

    public User(String userId, String userName, String userSecondName, String userEmail, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userSecondName = userSecondName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSecondName() {
        return userSecondName;
    }

    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
