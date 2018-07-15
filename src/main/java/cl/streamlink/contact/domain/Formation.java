package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 11/07/2018.
 */
public enum Formation {

    NOT_DEFINED,
    BAC,
    BAC_PLUS_2,
    BAC_PLUS_3,
    BAC_PLUS_4,
    BAC_PLUS_5,
    BAC_PLUS_6,
    BAC_PLUS_7,
    BAC_PLUS_8;

    @JsonCreator
    public static Formation fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}
