package cl.streamlink.contact.domain;


import cl.streamlink.contact.utils.MiscUtils;
import cl.streamlink.contact.utils.enums.Stage;

import javax.persistence.*;
import java.util.Optional;



@Entity
@Table(indexes = {@Index(name = "index_developer_reference", columnList = "reference", unique = true)})
public class Developer extends AbstractDevResProfile {

    @Enumerated(EnumType.STRING)
    private Stage stage = Stage.ToTreat;


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @Override
    public boolean equals(Object object) {
        return Optional.ofNullable(object).filter(obj -> obj instanceof Developer).map(obj -> (Developer) obj).
                filter(ag -> getId() == null || MiscUtils.equals(ag.getReference(), this.getReference())).
                filter(ag -> getId() != null || MiscUtils.equals(ag, this)).
                isPresent();
    }

    @Override
    public int hashCode() {
        if (this.getReference() != null)
            return this.getReference().hashCode();
        else if (this.getId() != null)
            return this.getId().hashCode();
        else
            return super.hashCode();
    }
}
