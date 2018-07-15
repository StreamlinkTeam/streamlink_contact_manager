package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 11/07/2018.
 */
public enum WorkTime {

    NOT_DEFINED,
    FULL_TIME,
    PART_TIME,
    HALF_TIME;

    @JsonCreator
    public static WorkTime fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}
