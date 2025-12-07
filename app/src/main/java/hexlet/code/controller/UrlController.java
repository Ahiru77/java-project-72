package hexlet.code.controller;
import hexlet.code.repository.UrlCheckRepository;
import io.javalin.http.Context;
import java.sql.SQLException;
import hexlet.code.util.NamedRoutes;
import hexlet.code.repository.UrlRepository;
import hexlet.code.model.Url;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;
import hexlet.code.dto.BasePage;
import hexlet.code.dto.url.UrlsPage;
import hexlet.code.dto.url.UrlPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.http.NotFoundResponse;

@Slf4j
public class UrlController {
    public static void build(Context ctx) {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("index.jte", model("page", page));
    }

    public static void urlsIndex(Context ctx) throws SQLException {
        ArrayList<Url> urlList = UrlRepository.getEntities();
        var latestChecks = UrlCheckRepository.findLatestCheck();
        for (Url url : urlList) {
            var id = url.getId();
            var lastCheck = latestChecks.get(id);
            url.setLastCheck(lastCheck);
        }
        var page = new UrlsPage(urlList);
        ctx.render("url/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity (id " + id + ") not found"));
        var urlChecks = UrlCheckRepository.find(id);
        var page = new UrlPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("url/show.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException, URISyntaxException, MalformedURLException {
        var urlString = ctx.formParam("url");
        if (urlString.isEmpty()) {
            ctx.sessionAttribute("flash", "Вы пропустили это поле");
            ctx.sessionAttribute("flash-type", "alert");
            ctx.redirect(NamedRoutes.mainPageRoute());
            return;
        }
        URL urlObj = null;
        try {
            URI uri = new URI(urlString);
            urlObj = uri.toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "alert");
            log.info("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        String protocol = urlObj.getProtocol();
        String host = urlObj.getHost();
        int port = urlObj.getPort();
        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(protocol).append("://").append(host);
        if (port != -1) {
            baseUrl.append(":").append(port);
        }

        var url = new URI(baseUrl.toString()).toURL();
        var urlRes = new Url(String.valueOf(url));
        if (UrlRepository.findByName(urlRes.getName()).isPresent()) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flash-type", "alert");
            ctx.redirect(NamedRoutes.mainPageRoute());
            return;
        }
        UrlRepository.save(urlRes);
        ctx.sessionAttribute("flash", "Страница успешно добавлена");
        ctx.sessionAttribute("flash-type", "success");
        ctx.redirect(NamedRoutes.urlsRoute());
    }
}
