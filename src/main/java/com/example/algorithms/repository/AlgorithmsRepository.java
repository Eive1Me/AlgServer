package com.example.algorithms.repository;

import com.example.algorithms.entity.Algorithms;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class AlgorithmsRepository implements IRestRepository<Algorithms>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id_PK\", \"name\" " +
            "FROM \"actor\" " +
            "ORDER BY \"id_PK\"";

    private static String selectByIdQuery = "SELECT \"id_PK\", \"name\" " +
            "FROM \"actor\" " +
            "WHERE \"id_PK\" = ?";

    private static String selectByName = "SELECT \"id_PK\", \"name\" " +
            "FROM \"actor\" " +
            "WHERE \"source_id\" = ?";

    private static String insertQuery = "INSERT INTO \"actor\"(\"name\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"id_PK\", \"name\"";

    private static String updateQuery = "UPDATE \"actor\" " +
            "SET \"name\" = ? " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"actor\" " +
            "WHERE \"id_PK\" = ? " +
            "RETURNING \"id_PK\", \"name\"";

    public AlgorithmsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Algorithms[] select() {
        ArrayList<Algorithms> values = new ArrayList<Algorithms>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Algorithms(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Algorithms[] result = new Algorithms[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Algorithms select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Algorithms(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    public Algorithms[] selectByName(Integer name) {
        ArrayList<Algorithms> values = new ArrayList<Algorithms>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new Algorithms(
                    rowSet.getInt(1),
                    rowSet.getString(2)
            ));
        }
        Algorithms[] result = new Algorithms[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Algorithms insert(Algorithms entity) {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Algorithms(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Algorithms update(Integer id, Algorithms entity) {
        Object[] params = new Object[] { id, entity.getName() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Algorithms(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Algorithms delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Algorithms(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}