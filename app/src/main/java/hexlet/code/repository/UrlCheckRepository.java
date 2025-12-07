package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class UrlCheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            var createdAt = LocalDateTime.now();
            preparedStatement.setLong(1, urlCheck.getUrlId());
            preparedStatement.setLong(2, urlCheck.getStatusCode());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getTitle());
            preparedStatement.setString(5, urlCheck.getDescription());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(createdAt));
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> find(Long id) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY created_at DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            var list = new ArrayList<UrlCheck>();
            while (resultSet.next()) {
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var statusCode = resultSet.getLong("status_code");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var h1 = resultSet.getString("h1");
                var urlId = resultSet.getLong("url_id");
                var checkId = resultSet.getLong("id");
                var urlCheck = new UrlCheck(statusCode, title, description, h1, urlId);
                urlCheck.setId(checkId);
                urlCheck.setCreatedAt(createdAt);
                list.add(urlCheck);
            }
            return list;
        }
    }

    public static Map<Long, UrlCheck> findLatestCheck() throws SQLException {
        var sql = "SELECT DISTINCT ON (url_id) * from url_checks order by url_id DESC, id DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var map = new HashMap<Long, UrlCheck>();
            while (resultSet.next()) {
                var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
                var statusCode = resultSet.getLong("status_code");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var h1 = resultSet.getString("h1");
                var urlId = resultSet.getLong("url_id");
                var checkId = resultSet.getLong("id");
                var urlCheck = new UrlCheck(statusCode, title, description, h1, urlId);
                urlCheck.setId(checkId);
                urlCheck.setCreatedAt(createdAt);
                map.put(urlId, urlCheck);
            }
            return map;
        }
    }
}
