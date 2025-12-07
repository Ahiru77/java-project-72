package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Slf4j
public final class Url {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private UrlCheck lastCheck;

    public Url(String name) {
        this.name = name;
    }
}
