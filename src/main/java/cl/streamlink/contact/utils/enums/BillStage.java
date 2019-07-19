package cl.streamlink.contact.utils.enums;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum BillStage {

    NOT_DEFINED,
    CREATION,
    TRANSMITTED_TO_CUSTOMER,
    REVIVAL1,
    REVIVAL2,
    EMAIL_TO_CUSTOMER,
    UNPAID,
    PAID;

    @JsonCreator
    public static BillStage fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(NOT_DEFINED)
                : NOT_DEFINED;
    }
}

