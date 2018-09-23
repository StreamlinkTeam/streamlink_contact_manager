package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by chemakh on 13/07/2018.
 */
public enum ActionType {

    NOTE,
    RECALL,
    CUSTOMER_PRESENTATION,
    TELEPHONE_INTERVIEW,
    PHYSICAL_INTERVIEW,
    CALL,
    EMAIL;

    @JsonCreator
    public static ActionType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOTE)
                : NOTE;
    }


}
