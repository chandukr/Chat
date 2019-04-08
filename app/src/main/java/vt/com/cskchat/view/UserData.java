package vt.com.cskchat.view;

public class UserData {

    private String userName;
    private String Email;
    private String Password;

    public UserData() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserData(String userName, String email, String password) {
        this.userName = userName;
        Email = email;
        Password = password;
    }
}
