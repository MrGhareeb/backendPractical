package bh.ghareeb.BackendPractical.Components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigComponent {
    public static String jsonDBPath;

    @Value("${json.database.file.path}")
    public void setJsonDBPath(String jsonPath) {
        this.jsonDBPath = jsonPath;
    }


}
