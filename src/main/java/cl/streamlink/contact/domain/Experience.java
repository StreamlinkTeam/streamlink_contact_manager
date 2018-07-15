package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 11/07/2018.
 */
public enum Experience {

    NOT_DEFINED,
    NON,
    BETWEEN1AND2,
    BETWEEN3AND5,
    BETWEEN6AND10,
    MORE_THAN_10;

    @JsonCreator
    public static Experience fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}
