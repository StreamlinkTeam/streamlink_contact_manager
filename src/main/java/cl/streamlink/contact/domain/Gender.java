package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Gender {
    M,
    F,
    UNDEFINED;

    @JsonCreator
    public static Gender fromString(final String gender) {

        if(MiscUtils.isNotEmpty(gender) && gender.equalsIgnoreCase("female"))
            return  F;

        if(MiscUtils.isNotEmpty(gender) && gender.equalsIgnoreCase("male"))
            return  M;


        return gender != null ?
                Arrays.stream(values())
                        .filter(val -> MiscUtils.equals(val.toString().toUpperCase(), gender.toUpperCase()))
                        .findFirst().orElse(UNDEFINED)
                : UNDEFINED;
    }

}
