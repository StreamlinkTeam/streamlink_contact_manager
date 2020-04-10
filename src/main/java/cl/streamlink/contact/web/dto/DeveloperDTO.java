package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.utils.enums.Stage;

public class DeveloperDTO extends AbstractDevResProfileDTO<Developer> {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isResource() {
        return false;
    }
}
