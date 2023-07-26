package cl.streamlink.contact.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import cl.streamlink.contact.utils.enums.ContractCategory;
import cl.streamlink.contact.utils.enums.ContractClassification;
import cl.streamlink.contact.utils.enums.ContractType;
import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.WorkTime;

@Entity
public class ContractHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String developerReference;

    private String responsibleReference;

    private String reference;

    private Currency currency;

    private BigDecimal salary;

    private ContractType type;

    private WorkTime workTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate trialPeriodEndDate;

    private LocalDate trialPeriodRenewalEndDate;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private BigDecimal cjm;

    private BigDecimal coefficient;

    private BigDecimal businessDays;

    private ContractCategory contractCategory;

    private ContractClassification contractClassification;

    private BigDecimal missionExpenses;

    public ContractHistory() {
    }

    public Long getId() {
        return id;
    }

    public String getDeveloperReference() {
        return developerReference;
    }

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public String getReference() {
        return reference;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ContractType getType() {
        return type;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getTrialPeriodEndDate() {
        return trialPeriodEndDate;
    }

    public LocalDate getTrialPeriodRenewalEndDate() {
        return trialPeriodRenewalEndDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public BigDecimal getCjm() {
        return cjm;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public BigDecimal getBusinessDays() {
        return businessDays;
    }

    public ContractCategory getContractCategory() {
        return contractCategory;
    }

    public ContractClassification getContractClassification() {
        return contractClassification;
    }

    public BigDecimal getMissionExpenses() {
        return missionExpenses;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setType(ContractType type) {
        this.type = type;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTrialPeriodEndDate(LocalDate trialPeriodEndDate) {
        this.trialPeriodEndDate = trialPeriodEndDate;
    }

    public void setTrialPeriodRenewalEndDate(LocalDate trialPeriodRenewalEndDate) {
        this.trialPeriodRenewalEndDate = trialPeriodRenewalEndDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setCjm(BigDecimal cjm) {
        this.cjm = cjm;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public void setBusinessDays(BigDecimal businessDays) {
        this.businessDays = businessDays;
    }

    public void setContractCategory(ContractCategory contractCategory) {
        this.contractCategory = contractCategory;
    }

    public void setContractClassification(ContractClassification contractClassification) {
        this.contractClassification = contractClassification;
    }

    public void setMissionExpenses(BigDecimal missionExpenses) {
        this.missionExpenses = missionExpenses;
    }

}
