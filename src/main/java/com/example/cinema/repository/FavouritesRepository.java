package com.example.cinema.repository;

import com.example.cinema.entity.Favourites;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class FavouritesRepository implements IRestRepository<Favourites> {
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id_PK\", \"name\" " +
            "FROM \"director\" " +
            "ORDER BY \"id_PK\"";

    private static String selectByIdQuery = "SELECT \"id_PK\", \"name\" " +
            "FROM \"director\" " +
            "WHERE \"id_PK\" = ?";

    private static String selectByName = "SELECT \"id_PK\", \"name\" " +
            "FROM \"director\" " +
            "WHERE \"source_id\" = ?";

    private static String insertQuery = "INSERT INTO \"director\"(\"name\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id_PK\", \"name\"";

    private static String updateQuery = "UPDATE \"director\" " +
            "SET \"name\" = ? " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"director\" " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"name\"";

    public FavouritesRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Favourites[] select() {
        ArrayList<Favourites> values = new ArrayList<Favourites>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Favourites(
                    rowSet.getInt(1),
                    rowSet.getString(2)
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
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Favourites[] selectByName(Integer name) {
        ArrayList<Favourites> values = new ArrayList<Favourites>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new Favourites(
                    rowSet.getInt(1),
                    rowSet.getString(2)
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
                rowSet.getInt(1),
                rowSet.getString(2)
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
                rowSet.getInt(1),
                rowSet.getString(2)
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
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}