import java.util.List;

public class RegisterUser {
    public static void main(String[] args) {
        ConnectionFactory.connect();
        UsersRepository repository = new UsersRepository();

        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setAge(27);
        newUser.setId(1);

        // Create User
        repository.create(newUser);

        // Update User
        repository.save(newUser);

        // Remove User
        repository.delete(3);

        // List All Users
        List<User> users = repository.findMany();

        for (User user: users) {
            System.out.println(user.getId() + " " + user.getName());
        }

        User user = repository.findById(2);

        if (user == null) {
            System.out.println("Register not found.");
        }

        System.out.println("Register founded with id " + user.getId() + " - " + user.getName());
    }
}
