package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 10/07/2018.
 */
public enum Stage {

    ToTreat,
    InTheProcessOfQualifying,
    Vivier,
    VivierPlus,
    ConvertedToResource,
    StopContacting;

    @JsonCreator
    public static Stage fromString(final String stage) {
        return stage != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), stage.toUpperCase()))
                        .findFirst().orElse(ToTreat)
                : ToTreat;
    }


}
