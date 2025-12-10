package gg.jte.generated.ondemand.url;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.TimeFormatter;
import hexlet.code.model.UrlCheck;
@SuppressWarnings("unchecked")
public final class JteshowGenerated {
	public static final String JTE_NAME = "url/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,3,5,5,7,7,9,9,9,15,15,15,19,19,19,23,23,23,29,29,29,29,43,43,45,45,45,46,46,46,47,47,47,48,48,48,49,49,49,50,50,50,52,52,56,56,56,56,56,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, hexlet.code.dto.url.UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"container-lg mt-5\">\r\n        <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n\r\n        <table class=\"table table-bordered table-hover mt-3\">\r\n            <tbody>\r\n            <tr>\r\n                <td>ID</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\r\n            </tr>\r\n            <tr>\r\n                <td>Имя</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\r\n            </tr>\r\n            <tr>\r\n                <td>Дата создания</td>\r\n                <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(TimeFormatter.format(page.getUrl().getCreatedAt()));
				jteOutput.writeContent("</td>\r\n            </tr>\r\n            </tbody>\r\n        </table>\r\n\r\n        <h2 class=\"mt-5\">Проверки</h2>\r\n        <form method=\"post\" action=\"/urls/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\">\r\n            <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\r\n        </form>\r\n\r\n        <table class=\"table table-bordered table-hover mt-3\">\r\n            <thead>\r\n            <th class=\"col-1\">ID</th>\r\n            <th class=\"col-1\">Код ответа</th>\r\n            <th>title</th>\r\n            <th>h1</th>\r\n            <th>description</th>\r\n            <th class=\"col-2\">Дата проверки</th>\r\n            </thead>\r\n            <tbody>\r\n            ");
				for (UrlCheck check : page.getUrlChecks()) {
					jteOutput.writeContent("\r\n                <tr>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getId());
					jteOutput.writeContent(" </td>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getStatusCode());
					jteOutput.writeContent(" </td>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getTitle());
					jteOutput.writeContent("  </td>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getH1());
					jteOutput.writeContent(" </td>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getDescription());
					jteOutput.writeContent(" </td>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(TimeFormatter.format(check.getCreatedAt()));
					jteOutput.writeContent(" </td>\r\n                </tr>\r\n            ");
				}
				jteOutput.writeContent("\r\n            </tbody>\r\n        </table>\r\n    </div>\r\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		hexlet.code.dto.url.UrlPage page = (hexlet.code.dto.url.UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
