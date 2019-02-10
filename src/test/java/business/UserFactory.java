package business;

import business.users.User;
import business.users.UserList;
import utility.Marshaller;

import java.util.List;

public class UserFactory {

    private static final String PATH = "src/test/resources/users.xml";

    private UserList getUsers() {
        return (UserList) Marshaller.unmarshall(UserList.class, PATH);
    }

    public List<User> getAllUsers() {
        return getUsers().getUser();
    }

    public User getUser(String name) {
        UserList users = getUsers();
        for (int i = 0; i < users.getUser().size(); i++) {
            if (users.getUser().get(i).name.equals(name)) {
                return users.getUser().get(i);
            }
        }
        throw new RuntimeException("No such user");
    }
}
