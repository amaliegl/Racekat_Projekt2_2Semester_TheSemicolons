package org.example.racekat_projekt2_2semester_thesemicolons.repository;

import org.example.racekat_projekt2_2semester_thesemicolons.model.Cat;
import org.example.racekat_projekt2_2semester_thesemicolons.model.User;
import org.example.racekat_projekt2_2semester_thesemicolons.repository.interfaces.ICatRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CatRepositoryMySql implements ICatRepository {

    private final JdbcTemplate jdbcTemplate;

    public CatRepositoryMySql(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCat(Cat cat, User user) {
        String sql = """
                INSERT INTO cats (
                    cat_name,
                    cat_birthday,
                    cat_sex,
                    cat_color,
                    cat_fertile,
                    cat_alive,
                    cat_image_path,
                    cat_pedigree_path) VALUES
                (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cat.getName()); // Set first parameter (name)
            ps.setDate(2, Date.valueOf(cat.getBirthday()));
            ps.setString(3, cat.getSex().name());
            ps.setString(4, cat.getColor().name());
            ps.setBoolean(5, cat.isFertile());
            ps.setBoolean(6, cat.isAlive());
            ps.setString(7, cat.getImagePath());
            ps.setString(8, cat.getPedigreePath());// Set second parameter (email)
            return ps;
        }, keyHolder);

        cat.setId(keyHolder.getKey().intValue());

        connectCatToUser(cat, user);
        user.addCatToCats(cat);
    }//TODO

    private void connectCatToUser(Cat cat, User user) {
        String sql = "INSERT INTO users_cats (user_id, cat_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, user.getId(), cat.getId());
    }

    @Override
    public void deleteCat(Cat cat) {
        /*String sql = "DELETE FROM cats WHERE cat_id = ?";

        jdbcTemplate.update(sql,
                cat.getOwnerId()
        );*/
    }//TODO - Hvad for en exception skal vi fange her?
    //TODO opdater uden ownerID

    @Override
    public void editCat(Cat cat) {
        String sql = """
                UPDATE cats SET                
                    cat_name = ?,
                    cat_color = ?,
                    cat_fertile = ?,
                    cat_alive = ?,
                    cat_image_path = ?,
                    cat_pedigree_path = ?
                    WHERE cat_id = ?
                """;

        jdbcTemplate.update(sql,
                cat.getName(),
                cat.getColor(),
                cat.isFertile(),
                cat.isAlive(),
                cat.getImagePath(),
                cat.getPedigreePath(),
                cat.getId()
        );
    }//TODO
}
