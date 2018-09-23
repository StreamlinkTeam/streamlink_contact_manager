package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * Created by Chemakh on 11/07/2018.
 */
public enum FamilySituation {

    SINGLE,
    MARRIED,
    COHABITATION,
    DIVORCED,
    WIDOWED,
    PACS;

    @JsonCreator
    public static FamilySituation fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(SINGLE)
                : SINGLE;
    }

}
