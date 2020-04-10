package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.Developer;
import cl.streamlink.contact.utils.enums.Stage;

/**
 * Created by chemakh on 12/07/2018.
 */
public class DeveloperResponseDTO extends AbstractDevResResponseDTO<Developer> {

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
