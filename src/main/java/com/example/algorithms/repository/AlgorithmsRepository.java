package com.example.algorithms.repository;

import com.example.algorithms.entity.Algorithms;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.UUID;

@Repository
public class AlgorithmsRepository implements IRestRepository<Algorithms>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\" " +
            "FROM \"Algorithms\" " +
            "ORDER BY \"id(PK)\"";

    private static String selectByIdQuery = "SELECT \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\"  " +
            "FROM \"Algorithms\" " +
            "WHERE \"id(PK)\" = ?";

    private static String selectByName = "SELECT \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\"  " +
            "FROM \"Algorithms\" " +
            "WHERE \"name\" = ?";

    private static String insertQuery = "INSERT INTO \"Algorithms\"(\"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\" ) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\" ";

    private static String updateQuery = "UPDATE \"Algorithms\" " +
            "SET \"name\" = ?, \"accuracy\" = ?, \"learning_time\" = ?, \"linear\" = ?, \"params\" = ?, \"notes\" = ?, \"how_works\" = ?, \"how_uses\" = ? " +
            "WHERE \"id(PK)\" = ? " +
            "RETURNING \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\" ";

    private static String deleteQuery = "DELETE FROM \"Algorithms\" " +
            "WHERE \"id(PK)\" = ? " +
            "RETURNING \"id(PK)\", \"name\", \"accuracy\", \"learning_time\", \"linear\", \"params\", \"notes\", \"how_works\", \"how_uses\" ";

    public AlgorithmsRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Algorithms[] select() {
        ArrayList<Algorithms> values = new ArrayList<Algorithms>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Algorithms(
                    UUID.fromString(rowSet.getString(1)),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getBoolean(5),
                    rowSet.getInt(6),
                    rowSet.getString(7),
                    rowSet.getString(8),
                    rowSet.getString(9)
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
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getBoolean(5),
                rowSet.getInt(6),
                rowSet.getString(7),
                rowSet.getString(8),
                rowSet.getString(9)
        );
    }

    public Algorithms[] selectByName(Integer name) {
        ArrayList<Algorithms> values = new ArrayList<Algorithms>();
        Object[] params = new Object[] { name };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByName, params, types);
        while (rowSet.next()) {
            values.add(new Algorithms(
                    UUID.fromString(rowSet.getString(1)),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getBoolean(5),
                    rowSet.getInt(6),
                    rowSet.getString(7),
                    rowSet.getString(8),
                    rowSet.getString(9)
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
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getBoolean(5),
                rowSet.getInt(6),
                rowSet.getString(7),
                rowSet.getString(8),
                rowSet.getString(9)
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
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getBoolean(5),
                rowSet.getInt(6),
                rowSet.getString(7),
                rowSet.getString(8),
                rowSet.getString(9)
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
                UUID.fromString(rowSet.getString(1)),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getBoolean(5),
                rowSet.getInt(6),
                rowSet.getString(7),
                rowSet.getString(8),
                rowSet.getString(9)
        );
    }
}