package hexlet.code.util;

public class NamedRoutes {

    public static String mainPageRoute() {
        return "/";
    }
    public static String urlsRoute() {
        return "/urls";
    }
    public static String urlChecksRoute(String id) {
        return "/urls/" + id + "/checks";
    }
    public static String urlRoute(String id) {
        return "/urls/" + id;
    }
    public static String urlRoute(Long id) {
        return urlRoute(String.valueOf(id));
    }
}
