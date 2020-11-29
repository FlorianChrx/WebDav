package controller;

import connection.ConnectionManager;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserController implements Crud<String, User> {

    @Override
    public Optional<User> findById(String key) {
        try {
            PreparedStatement ps = ConnectionManager.getConnnection().prepareStatement("select * from users where username = ?");
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return Optional.of(new User(rs.getString(1), rs.getString(2)));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Collection<User> findAll() {
        List<User> res = new ArrayList<>();
        try {
            PreparedStatement ps = ConnectionManager.getConnnection().prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) res.add(new User(rs.getString(1), rs.getString(2)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean save(User value) {
        try {
            PreparedStatement ps = ConnectionManager.getConnnection().prepareStatement("insert into users values (?,?)");
            ps.setString(1, value.getUsername());
            ps.setString(2, value.getPassword());
            int res = ps.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modify(String key, User value) {
        try {
            PreparedStatement ps = ConnectionManager.getConnnection().prepareStatement("update users set username = ?, password = ? where username = ?");
            ps.setString(1, value.getUsername());
            ps.setString(2, value.getPassword());
            ps.setString(3, value.getUsername());
            int res = ps.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String key) {
        try {
            PreparedStatement ps = ConnectionManager.getConnnection().prepareStatement("delete from users where username = ?");
            ps.setString(1, key);
            int res = ps.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
