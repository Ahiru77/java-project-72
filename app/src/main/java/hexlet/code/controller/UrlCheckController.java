package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import lombok.extern.slf4j.Slf4j;
import java.sql.SQLException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class UrlCheckController {
    public static void createCheck(Context ctx) throws UnirestException, SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity (id " + id + ") not found"));
        var urlName = url.getName();

        HttpResponse<String> response = Unirest.get(urlName).asString();
        String htmlBody = response.getBody();
        Document doc = Jsoup.parse(htmlBody);

        long statusCode = response.getStatus();
        log.info("Status code: " + statusCode);

        String title = doc.title();
        log.info("Title: " + title);

        Elements h1Elements = doc.select("h1");
        String h1 = h1Elements.isEmpty() ? "Not found" : h1Elements.first().text();
        log.info("H1: " + h1);

        String description = doc.select("meta[name=description]").attr("content");
        log.info("Description: " + description);

        var urlCheck = new UrlCheck(statusCode, title, description, h1, id);
        UrlCheckRepository.save(urlCheck);
        ctx.sessionAttribute("flash", "Страница успешно проверена");
        ctx.sessionAttribute("flash-type", "success");
        ctx.redirect(NamedRoutes.urlRoute(id));
    }
}
