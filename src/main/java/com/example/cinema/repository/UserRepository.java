package com.example.cinema.repository;

import com.example.cinema.entity.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class UserRepository implements IRestRepository<User> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id_PK\", \"genre\" " +
            "FROM \"genre\" " +
            "ORDER BY \"id_PK\"";

    private static String selectByIdQuery = "SELECT \"id_PK\", \"genre\" " +
            "FROM \"genre\" " +
            "WHERE \"id_PK\" = ?";

    private static String selectByName = "SELECT \"id_PK\", \"genre\" " +
            "FROM \"genre\" " +
            "WHERE \"genre\" = ?";

    private static String insertQuery = "INSERT INTO \"genre\"(\"genre\") " +
            "VALUES (?) " +
            "RETURNING \"id_PK\", \"genre\"";

    private static String updateQuery = "UPDATE \"genre\" " +
            "SET \"genre\" = ? " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"genre\"";

    private static String deleteQuery = "DELETE FROM \"genre\" " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"genre\"";

    public UserRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public User[] select() {
        ArrayList<User> values = new ArrayList<User>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new User(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        User[] result = new User[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public User select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                    rowSet.getString(2)
        );
    }

    public User[] selectByName(Integer name) {
        ArrayList<User> values = new ArrayList<User>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new User(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        User[] result = new User[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public User insert(User entity) {
        Object[] params = new Object[] { entity.getGenre() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                    rowSet.getString(2)
        );
    }

    @Override
    public User update(Integer id, User entity) {
        Object[] params = new Object[] { id, entity.getGenre() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                    rowSet.getString(2)
        );
    }

    @Override
    public User delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
                rowSet.getInt(1),
                    rowSet.getString(2)
        );
    }
}
