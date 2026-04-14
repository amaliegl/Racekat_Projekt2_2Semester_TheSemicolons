package org.example.racekat_projekt2_2semester_thesemicolons.repository;

import org.example.racekat_projekt2_2semester_thesemicolons.model.*;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.IUserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryMySql implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryMySql(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = """
                SELECT
                user_id,
                user_name,
                user_email,
                user_role,
                user_phone
                FROM users
                """;
        //Intentionally selecting specifik columns to avoid fetching passwords

        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        Role_ENUM.valueOf(rs.getString("user_role")),
                        rs.getString("user_phone")
                ));

        //TODO - spørgsmål: skal katte kobles på her, eller skal det rundt om service først?
        for (int i = 0; i < users.size(); i++) {
            assignUserTheirCats(users.get(i));
        }

        return users;
    }//TODO

    public User assignUserTheirCats(User user) {
        String sql = """
                SELECT * FROM cats
                JOIN users_cats ON cats.cat_id = users_cats.cat_id
                WHERE user_id = ?
                """;

        List<Cat> cats = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Cat(
                        rs.getInt("cat_id"),
                        rs.getString("cat_name"),
                        rs.getDate("cat_birthday").toLocalDate(),
                        Color_ENUM.valueOf(rs.getString("cat_color")),
                        Sex_ENUM.valueOf(rs.getString("cat_sex")),
                        rs.getBoolean("cat_fertile"),
                        rs.getBoolean("cat_alive"),
                        rs.getString("cat_image_path"),
                        rs.getString("cat_pedigree_path")
                ), user.getId()
        );

        user.setCats(cats);
        return user;
    }

    @Override
    public User createUser(User user) throws NullPointerException{

        String sql = """
                INSERT INTO users (
                    user_name,
                    user_email,
                    user_password,
                    user_role,
                    user_phone) VALUES
                (?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, Role_ENUM.Member.name());
            ps.setString(5, user.getPhone());
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().intValue());

        return user;
    }//TODO - hashing spørgsmål

    @Override
    public void deleteUser(User user) {
        String sql = """
                DELETE FROM users WHERE user_id =?
                """;
        jdbcTemplate.update(sql,
                user.getId());
    }//TODO

    @Override
    public void editUser(User user) {
        String sql = """
                UPDATE users
                SET user_name = ?,
                    user_email = ?,
                    user_role = ?,
                    user_phone = ?
                WHERE ?
                """;

        jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getId()
                );
    }//TODO - lige nu ingen ændring af password || udvidelse/senere: ændring af password

    @Override
    public User login(String email, String password) {
        return null;
    }//TODO - hashing spørgsmål

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = """
                SELECT * FROM users WHERE user_email = ? LIMIT 1
                """;
        //TODO - LIMIT 1 is for testing

        List<User> results = jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        Role_ENUM.valueOf(rs.getString("user_role")),
                        rs.getString("user_phone")
                ), email
        );

        if (results.isEmpty()) {
            return Optional.empty();
        }

        assignUserTheirCats(results.get(0));
        return Optional.of(results.get(0));
    }
}
