package cl.streamlink.contact.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Chemakh on 11/07/2018.
 */

@Entity
@Table(indexes = {@Index(name = "index_contract_reference", columnList = "reference", unique = true)})
@EntityListeners(AuditingEntityListener.class)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String reference;

    @OneToOne
    private Developer developer;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @Enumerated(EnumType.STRING)
    private WorkTime workTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate trialPeriodeEndDate;

    private LocalDate trialPeriodeRenewalEndDate;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getTrialPeriodeEndDate() {
        return trialPeriodeEndDate;
    }

    public void setTrialPeriodeEndDate(LocalDate trialPeriodeEndDate) {
        this.trialPeriodeEndDate = trialPeriodeEndDate;
    }

    public LocalDate getTrialPeriodeRenewalEndDate() {
        return trialPeriodeRenewalEndDate;
    }

    public void setTrialPeriodeRenewalEndDate(LocalDate trialPeriodeRenewalEndDate) {
        this.trialPeriodeRenewalEndDate = trialPeriodeRenewalEndDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
