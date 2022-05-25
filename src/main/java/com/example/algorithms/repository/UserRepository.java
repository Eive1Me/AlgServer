package com.example.algorithms.repository;

import com.example.algorithms.entity.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class UserRepository implements IRestRepository<User> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\" " +
            "FROM \"Users\" " +
            "ORDER BY \"id(PK)\"";

    private static String selectByIdQuery = "SELECT \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\" " +
            "FROM \"Users\" " +
            "WHERE \"id(PK)\" = ?";

    private static String selectByName = "SELECT \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\" " +
            "FROM \"Users\" " +
            "WHERE \"login\" = ?";

    private static String insertQuery = "INSERT INTO \"Users\"(\"login\") " +
            "VALUES (?) " +
            "RETURNING \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\"";

    private static String updateQuery = "UPDATE \"Users\" " +
            "SET \"id(PK)\" = ?, \"login\" = ?, \"password\" = ?, \"favourites\" = ?, \"role\" = ? " +
            "WHERE \"id(PK)\" = ? " +
            "RETURNING \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\"";

    private static String deleteQuery = "DELETE FROM \"Users\" " +
            "WHERE \"id(PK)\" = ? " +
            "RETURNING \"id(PK)\", \"login\", \"password\", \"favourites\", \"role\"";

    public UserRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public User[] select() {
        ArrayList<User> values = new ArrayList<User>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new User(
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                UUID.fromString(rowSet.getString(4)),
                rowSet.getString(5)
            ));
        }
        User[] result = new User[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public User select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
            UUID.fromString(rowSet.getString(1)),
            rowSet.getString(2),
            rowSet.getString(3),
            UUID.fromString(rowSet.getString(4)),
            rowSet.getString(5)
        );
    }

    public User[] selectByName(Integer name) {
        ArrayList<User> values = new ArrayList<User>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new User(
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                UUID.fromString(rowSet.getString(4)),
                rowSet.getString(5)
            ));
        }
        User[] result = new User[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public User insert(User entity) {
        Object[] params = new Object[] { entity.getLogin() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
            UUID.fromString(rowSet.getString(1)),
            rowSet.getString(2),
            rowSet.getString(3),
            UUID.fromString(rowSet.getString(4)),
            rowSet.getString(5)
        );
    }

    @Override
    public User update(Integer id, User entity) {
        Object[] params = new Object[] { id, entity.getLogin() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
            UUID.fromString(rowSet.getString(1)),
            rowSet.getString(2),
            rowSet.getString(3),
            UUID.fromString(rowSet.getString(4)),
            rowSet.getString(5)
        );
    }

    @Override
    public User delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new User(
            UUID.fromString(rowSet.getString(1)),
            rowSet.getString(2),
            rowSet.getString(3),
            UUID.fromString(rowSet.getString(4)),
            rowSet.getString(5)
        );
    }
}
