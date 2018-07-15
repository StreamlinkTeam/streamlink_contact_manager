package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Radouen on 13/07/2018.
 */
public enum ActionType {

    NOTE,
    RECALL,
    CUSTOMER_PRESENTATION,
    TELEPHONE_INTERVIEW,
    PHYSICAL_MAINTENANCE,
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
