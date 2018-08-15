package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.MiscUtils;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;
import java.util.List;

public enum SocietyActivityArea {

    Other,
    Aerospace,
    Agri,
    Insurance,
    Automotive,
    Bank,
    Buildings,
    Biomedical,
    Chemistry,
    Advice,
    Defense,
    SoftwarePublishing,
    Energy,
    Environment,
    rail,
    LargeDistribution,
    Infrastructure,
    Logistics,
    Media,
    MetalSteelIndustry,
    Naval,
    Nuclear,
    OilAndGas,
    petrochemicals,
    Pharmacy,
    Health,
    PublicSector,
    Services,
    Telecommunications;

    @JsonCreator
    public static SocietyActivityArea fromString(final String value) {
        return value != null ?
                Arrays.stream(values()).filter(val -> MiscUtils.equals(val.toString().toUpperCase(), value.toUpperCase()))
                        .findFirst().orElse(Other)
                : Other;
    }

    public static List<SocietyActivityArea> getAll() {
        return Arrays.asList(values());
    }
}
