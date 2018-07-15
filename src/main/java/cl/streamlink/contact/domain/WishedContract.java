package cl.streamlink.contact.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Chemakh on 11/07/2018.
 */

@Entity
@Table(indexes = {@Index(name = "index_wished_contract_reference", columnList = "reference", unique = true)})
public class WishedContract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @OneToOne
    private Developer developer;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal currentSalary;

    @Enumerated(EnumType.STRING)
    private ContractType wishedContractType;

    private BigDecimal wishedSalaryMax;

    private BigDecimal wishedSalaryMin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(BigDecimal currentSalary) {
        this.currentSalary = currentSalary;
    }

    public ContractType getWishedContractType() {
        return wishedContractType;
    }

    public void setWishedContractType(ContractType wishedContractType) {
        this.wishedContractType = wishedContractType;
    }

    public BigDecimal getWishedSalaryMax() {
        return wishedSalaryMax;
    }

    public void setWishedSalaryMax(BigDecimal wishedSalaryMax) {
        this.wishedSalaryMax = wishedSalaryMax;
    }

    public BigDecimal getWishedSalaryMin() {
        return wishedSalaryMin;
    }

    public void setWishedSalaryMin(BigDecimal wishedSalaryMin) {
        this.wishedSalaryMin = wishedSalaryMin;
    }
}
