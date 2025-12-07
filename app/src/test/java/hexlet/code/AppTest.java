package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.SQLException;

import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.MockResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import hexlet.code.App;

import hexlet.code.model.Url;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class AppTest {
    private static MockWebServer mockWebServer;
    private Javalin app;
    final String FIXTURE = "src/test/resources/fixtures/page_test.html";
    String mock = "http://localhost:";

    public static String readFile(String filePath) throws IOException {
            return Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
    }

    @BeforeEach
     public final void setUp() throws IOException, SQLException {
        app = App.getApp();
        mockWebServer = new MockWebServer();
        MockResponse mockedResponse = new MockResponse()
                .setBody(readFile(FIXTURE));
        mockWebServer.enqueue(mockedResponse);
        mockWebServer.start();
    }

    @AfterEach
    public final void tearDown() throws IOException, SQLException {
        UrlRepository.clean();
        mockWebServer.shutdown();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testUsersPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/url/");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    void testUrlNotFound() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }

    @Test
    public void testCreateUrl() {
        JavalinTest.test(app, (server, client) -> {
            mock = mock + mockWebServer.getPort();
            var response = client.post(NamedRoutes.urlsRoute(), "url=" + mock);
            UrlRepository.findByName(mock);
            assertThat(UrlRepository.findByName(mock)).isNotEqualTo(null);
        });
    }

    @Test
    public void testShowUrl() {
        JavalinTest.test(app, (server, client) -> {
            mock = mock + mockWebServer.getPort();
            var response = client.post(NamedRoutes.urlsRoute(), "url=" + mock);
            var responseShow = client.get("/urls/1");
            assertThat(responseShow.code()).isEqualTo(200);
        });
    }
}