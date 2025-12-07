package gg.jte.generated.ondemand.url;
import hexlet.code.util.NamedRoutes;
import hexlet.code.model.Url;
import hexlet.code.util.TimeFormatter;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "url/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,3,5,5,7,7,21,21,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,26,26,27,27,27,28,28,31,31,32,32,32,33,33,36,36,40,40,40,40,40,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, hexlet.code.dto.url.UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <div class=\"container-lg mt-5\">\r\n        <h1>Сайты</h1>\r\n\r\n        <table class=\"table table-bordered table-hover mt-3\">\r\n            <thead>\r\n            <tr>\r\n                <th class=\"col-1\">ID</th>\r\n                <th>Имя</th>\r\n                <th class=\"col-2\">Последняя проверка</th>\r\n                <th class=\"col-1\">Код ответа</th>\r\n            </tr>\r\n            </thead>\r\n            <tbody>\r\n            ");
				for (Url url : page.getUrls()) {
					jteOutput.writeContent("\r\n                <tr>\r\n                    <td> ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent(" </td>\r\n                    <td> <a");
					var __jte_html_attribute_0 = NamedRoutes.urlRoute(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a> </td>\r\n                    <td>\r\n                        ");
					if (url.getLastCheck() != null) {
						jteOutput.writeContent("\r\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(TimeFormatter.format(url.getLastCheck().getCreatedAt()));
						jteOutput.writeContent("\r\n                        ");
					}
					jteOutput.writeContent("\r\n                    </td>\r\n                    <td>\r\n                        ");
					if (url.getLastCheck() != null) {
						jteOutput.writeContent("\r\n                            ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getLastCheck().getStatusCode());
						jteOutput.writeContent("\r\n                        ");
					}
					jteOutput.writeContent("\r\n                    </td>\r\n                </tr>\r\n            ");
				}
				jteOutput.writeContent("\r\n            </tbody>\r\n        </table>\r\n    </div>\r\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		hexlet.code.dto.url.UrlsPage page = (hexlet.code.dto.url.UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
