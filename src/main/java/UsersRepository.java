import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private final Connection connection;

    public UsersRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public void create(User user) {
        try {
            String insertSQL = "INSERT INTO public.users (name, age) VALUES(?,?);";

            PreparedStatement pst = connection.prepareStatement(insertSQL);
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAge());
            pst.execute();

            System.out.println("User registered successfully");
        } catch (Exception ex) {
          ex.printStackTrace();
        }
    }

    public void save(User user) {
        try {
            String updateSQL = "UPDATE public.users SET name = ?, age = ? WHERE id=?;";

            PreparedStatement pst = connection.prepareStatement(updateSQL);
            pst.setString(1, user.getName());
            pst.setInt(2, user.getAge());
            pst.setInt(3, user.getId());
            pst.execute();

            System.out.println("User updated successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String deleteSQL = "DELETE FROM public.users WHERE id = ?;";

            PreparedStatement pst = connection.prepareStatement(deleteSQL);
            pst.setInt(1, id);
            pst.execute();

            System.out.println("User removed successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<User> findMany () {
        List<User> users = new ArrayList<User>();

        try {
            String selectSQL = "SELECT * FROM public.users;";

            PreparedStatement statement = connection.prepareStatement(selectSQL);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String columnName = result.getString("name");
                int columnAge = result.getInt("age");
                int columnId = result.getInt("id");
                User user = new User();
                user.setId(columnId);
                user.setName(columnName);
                user.setAge(columnAge);

                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public User findById(int userId) {
        User user = null;

        try {
            String selectSQL = "SELECT * FROM public.users WHERE id = ?;";

            PreparedStatement statement = connection.prepareStatement(selectSQL);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String columnName = result.getString("name");
                int columnAge = result.getInt("age");
                int columnId = result.getInt("id");

                user = new User();
                user.setId(columnId);
                user.setName(columnName);
                user.setAge(columnAge);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }
}
