package hexlet.code.dto.url;

import hexlet.code.dto.BasePage;
import hexlet.code.model.Url;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
public class UrlsPage extends BasePage {
    private List<Url> urls;
}
