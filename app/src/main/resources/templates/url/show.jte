@import hexlet.code.util.NamedRoutes
@import hexlet.code.util.TimeFormatter
@import hexlet.code.model.UrlCheck
@param hexlet.code.dto.url.UrlPage page

@template.layout.page(
page = page,
content = @`
    <div class="container-lg mt-5">
        <h1>Сайт: ${page.getUrl().getName()}</h1>

        <table class="table table-bordered table-hover mt-3">
            <tbody>
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${TimeFormatter.format(page.getUrl().getCreatedAt())}</td>
            </tr>
            </tbody>
        </table>

        <h2 class="mt-5">Проверки</h2>
        <form method="post" action="/urls/${page.getUrl().getId()}/checks">
            <button type="submit" class="btn btn-primary">Запустить проверку</button>
        </form>

        <table class="table table-bordered table-hover mt-3">
            <thead>
            <th class="col-1">ID</th>
            <th class="col-1">Код ответа</th>
            <th>title</th>
            <th>h1</th>
            <th>description</th>
            <th class="col-2">Дата проверки</th>
            </thead>
            <tbody>
            @for(UrlCheck check : page.getUrlChecks())
                <tr>
                    <td> ${check.getId()} </td>
                    <td> ${check.getStatusCode()} </td>
                    <td> ${check.getTitle()}  </td>
                    <td> ${check.getH1()} </td>
                    <td> ${check.getDescription()} </td>
                    <td> ${TimeFormatter.format(check.getCreatedAt())} </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>
`)