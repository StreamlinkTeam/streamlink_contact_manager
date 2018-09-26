package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.ContractType;
import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.WorkTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Chemakh on 11/07/2018.
 */
public class ContractDTO {

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

    public String getDeveloperReference() {
        return developerReference;
    }

    public void setDeveloperReference(String developerReference) {
        this.developerReference = developerReference;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public String getResponsibleReference() {
        return responsibleReference;
    }

    public void setResponsibleReference(String responsibleReference) {
        this.responsibleReference = responsibleReference;
    }

    public ContractType getType() {
        return type;
    }

    public void setType(ContractType type) {
        this.type = type;
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

    public LocalDate getTrialPeriodEndDate() {
        return trialPeriodEndDate;
    }

    public void setTrialPeriodEndDate(LocalDate trialPeriodEndDate) {
        this.trialPeriodEndDate = trialPeriodEndDate;
    }

    public LocalDate getTrialPeriodRenewalEndDate() {
        return trialPeriodRenewalEndDate;
    }

    public void setTrialPeriodRenewalEndDate(LocalDate trialPeriodRenewalEndDate) {
        this.trialPeriodRenewalEndDate = trialPeriodRenewalEndDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
