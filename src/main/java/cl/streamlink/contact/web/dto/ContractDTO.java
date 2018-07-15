package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.ContractType;
import cl.streamlink.contact.domain.Currency;
import cl.streamlink.contact.domain.WorkTime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Chemakh on 11/07/2018.
 */
public class ContractDTO {

    private String developerReference;

    private String reference;

    private Currency currency;

    private BigDecimal salary;

    private ContractType contractType;

    private WorkTime workTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate trialPeriodeEndDate;

    private LocalDate trialPeriodeRenewalEndDate;

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
