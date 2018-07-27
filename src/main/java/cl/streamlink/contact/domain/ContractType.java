package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 10/07/2018.
 */
public enum ContractType {

    NOT_DEFINED,
    CDI,
    CDD,
    SUBCONTRACTOR,
    FREELANCE,
    TRAINEESHIP;

    @JsonCreator
    public static ContractType fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}
