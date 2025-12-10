package hexlet.code;

import java.io.IOException;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.controller.UrlCheckController;
import hexlet.code.repository.BaseRepository;
import hexlet.code.controller.UrlController;
import io.javalin.Javalin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.util.NamedRoutes;

@Slf4j
public class App {
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(getPort());
    }

    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {
//        var dbVar = System.getenv().getOrDefault("JDBC_DATABASE_URL",
//                "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        String dbVar = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(dbVar);

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readResourceFile("schema.sql");

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

//      app.before(ctx -> {
//          ctx.contentType("text/html; charset=utf-8");
//      });

        app.get(NamedRoutes.mainPageRoute(), UrlController::build);
        app.post(NamedRoutes.urlsRoute(), UrlController::create);
        app.get(NamedRoutes.urlsRoute(), UrlController::urlsIndex);
        app.get(NamedRoutes.urlRoute("{id}"), UrlController::show);
        app.post(NamedRoutes.urlChecksRoute("{id}"), UrlCheckController::createCheck);
        return app;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }
}
