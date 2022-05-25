package com.example.algorithms.repository;

import com.example.algorithms.entity.Favourites;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class FavouritesRepository implements IRestRepository<Favourites> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"user_id\", \"algorithm_id\" " +
            "FROM \"Favourites\" " +
            "ORDER BY \"user_id\"";

    private static String selectByIdQuery = "SELECT \"user_id\", \"algorithm_id\" " +
            "FROM \"Favourites\" " +
            "WHERE \"user_id\" = ?";

    private static String selectByAlgorithm = "SELECT \"user_id\", \"algorithm_id\" " +
            "FROM \"Favourites\" " +
            "WHERE \"algorithms_id\" = ?";

    private static String insertQuery = "INSERT INTO \"Favourites\" (\"user_id\", \"algorithm_id\") " +
            "VALUES (?, ?) " +
            "RETURNING \"user_id\", \"algorithm_id\" ";

    private static String updateQuery = "UPDATE \"Favourites\" " +
            "SET \"user_id\" = ?, \"algorithm_id\" = ? " +
            "WHERE \"user_id\" = ? " +
            "RETURNING \"user_id\", \"algorithm_id\"";

    private static String deleteQuery = "DELETE FROM \"Favourites\" " +
            "WHERE \"user_id\" = ? " +
            "RETURNING \"user_id\", \"algorithm_id\" ";

    public FavouritesRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Favourites[] select() {
        ArrayList<Favourites> values = new ArrayList<Favourites>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Favourites(
                UUID.fromString(rowSet.getString(1)),
                UUID.fromString(rowSet.getString(2))
            ));
        }
        Favourites[] result = new Favourites[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Favourites select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Favourites(
            UUID.fromString(rowSet.getString(1)),
            UUID.fromString(rowSet.getString(2))
        );
    }

    public Favourites[] selectByName(Integer name) {
        ArrayList<Favourites> values = new ArrayList<Favourites>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAlgorithm, params, types);
        while (rowSet.next()) {
            values.add(new Favourites(
                UUID.fromString(rowSet.getString(1)),
                UUID.fromString(rowSet.getString(2))
            ));
        }
        Favourites[] result = new Favourites[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Favourites insert(Favourites entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Favourites(
            UUID.fromString(rowSet.getString(1)),
            UUID.fromString(rowSet.getString(2))
        );
    }

    @Override
    public Favourites update(Integer id, Favourites entity) {
        Object[] params = new Object[] { id, entity.getName() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Favourites(
            UUID.fromString(rowSet.getString(1)),
            UUID.fromString(rowSet.getString(2))
        );
    }

    @Override
    public Favourites delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Favourites(
            UUID.fromString(rowSet.getString(1)),
            UUID.fromString(rowSet.getString(2))
        );
    }
}