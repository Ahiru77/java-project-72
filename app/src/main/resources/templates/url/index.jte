@import hexlet.code.util.NamedRoutes
@import hexlet.code.model.Url
@import hexlet.code.util.TimeFormatter
@param hexlet.code.dto.url.UrlsPage page

@template.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайты</h1>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <tr>
                <th class="col-1">ID</th>
                <th>Имя</th>
                <th class="col-2">Последняя проверка</th>
                <th class="col-1">Код ответа</th>
            </tr>
            </thead>
            <tbody>
            @for(Url url : page.getUrls())
                <tr>
                    <td> ${url.getId()} </td>
                    <td> <a href="${NamedRoutes.urlRoute(url.getId())}">${url.getName()}</a> </td>
                    <td>
                        @if (url.getLastCheck() != null)
                            ${TimeFormatter.format(url.getLastCheck().getCreatedAt())}
                        @endif
                    </td>
                    <td>
                        @if (url.getLastCheck() != null)
                            ${url.getLastCheck().getStatusCode()}
                        @endif
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`)