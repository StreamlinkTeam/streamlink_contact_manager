package cl.streamlink.contact.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "familyElement")
public class FamilyElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String familySituation;

    private Integer numberOfChildren;

    private LocalDate weddingDate;

    private String partnerFirstName;

    private String partnerLastName;

    private String firstChildrenFirstName;

    private String secondChildrenFirstName;

    private String thirdChildrenFirstName;

    private String fourthChildrenFirstName;

    private String fifthChildrenFirstName;

    public FamilyElement() {
    }

    public FamilyElement(@NotNull String familySituation, Integer numberOfChildren, LocalDate weddingDate,
            String partnerFirstName, String partnerLastName, String firstChildrenFirstName,
            String secondChildrenFirstName, String thirdChildrenFirstName, String fourthChildrenFirstName,
            String fifthChildrenFirstName) {
        this.familySituation = familySituation;
        this.numberOfChildren = numberOfChildren;
        this.weddingDate = weddingDate;
        this.partnerFirstName = partnerFirstName;
        this.partnerLastName = partnerLastName;
        this.firstChildrenFirstName = firstChildrenFirstName;
        this.secondChildrenFirstName = secondChildrenFirstName;
        this.thirdChildrenFirstName = thirdChildrenFirstName;
        this.fourthChildrenFirstName = fourthChildrenFirstName;
        this.fifthChildrenFirstName = fifthChildrenFirstName;
    }

    public Long getId() {
        return id;
    }

    public String getFamilySituation() {
        return familySituation;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public LocalDate getWeddingDate() {
        return weddingDate;
    }

    public String getPartnerFirstName() {
        return partnerFirstName;
    }

    public String getPartnerLastName() {
        return partnerLastName;
    }

    public String getFirstChildrenFirstName() {
        return firstChildrenFirstName;
    }

    public String getSecondChildrenFirstName() {
        return secondChildrenFirstName;
    }

    public String getThirdChildrenFirstName() {
        return thirdChildrenFirstName;
    }

    public String getFourthChildrenFirstName() {
        return fourthChildrenFirstName;
    }

    public String getFifthChildrenFirstName() {
        return fifthChildrenFirstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamilySituation(String familySituation) {
        this.familySituation = familySituation;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setWeddingDate(LocalDate weddingDate) {
        this.weddingDate = weddingDate;
    }

    public void setPartnerFirstName(String partnerFirstName) {
        this.partnerFirstName = partnerFirstName;
    }

    public void setPartnerLastName(String partnerLastName) {
        this.partnerLastName = partnerLastName;
    }

    public void setFirstChildrenFirstName(String firstChildrenFirstName) {
        this.firstChildrenFirstName = firstChildrenFirstName;
    }

    public void setSecondChildrenFirstName(String secondChildrenFirstName) {
        this.secondChildrenFirstName = secondChildrenFirstName;
    }

    public void setThirdChildrenFirstName(String thirdChildrenFirstName) {
        this.thirdChildrenFirstName = thirdChildrenFirstName;
    }

    public void setFourthChildrenFirstName(String fourthChildrenFirstName) {
        this.fourthChildrenFirstName = fourthChildrenFirstName;
    }

    public void setFifthChildrenFirstName(String fifthChildrenFirstName) {
        this.fifthChildrenFirstName = fifthChildrenFirstName;
    }

}
