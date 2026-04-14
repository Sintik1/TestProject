package config;

import java.io.InputStream;
import java.util.Properties;

public class Env {

    public static String baseUri() {
        String fromEnv = System.getenv("BASE_URI");
        if (fromEnv != null&& !fromEnv.isBlank()){
            return trimSlash(fromEnv);
        }
        String fromProp = System.getProperty("baseUri");
        if (fromProp != null && !fromProp.isBlank()) return trimSlash(fromProp);
        String fromFile = readFromApplicationProperties("url");
        if (fromFile != null && !fromFile.isBlank()) return trimSlash(fromFile);
        throw new IllegalStateException(
                "Base URI is not set. Set env BASE_URI or -DbaseUri=... or application.properties url=..."
        );
    }
    private static String readFromApplicationProperties(String key) {
        try (InputStream in = Env.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (in == null) return null;
            Properties p = new Properties();
            p.load(in);
            return p.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read application.properties", e);
        }
    }
    private static String trimSlash(String url) {
        // чтобы не плодить // в конкатенации
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }
}



