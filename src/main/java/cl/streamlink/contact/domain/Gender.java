package cl.streamlink.contact.domain;



import cl.streamlink.contact.utils.MiscUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Gender
{
    M,
    F,
    UNDEFINED;

    public static Gender fromString(final String sex)
    {

        return sex != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString(), sex.toUpperCase().trim())).findFirst().orElse(UNDEFINED)
                : UNDEFINED;

    }

    public static boolean isMan(Gender sex)
    {

        return sex == M;
    }

    private static final List<Gender> VALUES = Collections.unmodifiableList(Arrays.asList(Gender.F, Gender.M));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Gender randomSex()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }


}
