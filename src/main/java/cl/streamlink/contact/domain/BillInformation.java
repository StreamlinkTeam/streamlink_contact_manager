package cl.streamlink.contact.domain;

import cl.streamlink.contact.utils.enums.BillStage;
import cl.streamlink.contact.utils.enums.Currency;
import cl.streamlink.contact.utils.enums.PaymentType;

import javax.persistence.*;
import java.time.LocalDate;

@Embeddable
public class BillInformation {

    private String state;

    private LocalDate billDate;

    private LocalDate billStartDate;

    private LocalDate billEndDate;

    private LocalDate scheduledDate;

    private LocalDate receiptDate;

    private float tva;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private BillStage billStage;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private float discountRate;

    private String note;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getBillStartDate() {
        return billStartDate;
    }

    public void setBillStartDate(LocalDate billStartDate) {
        this.billStartDate = billStartDate;
    }

    public LocalDate getBillEndDate() {
        return billEndDate;
    }

    public void setBillEndDate(LocalDate billEndDate) {
        this.billEndDate = billEndDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BillStage getBillStage() {
        return billStage;
    }

    public void setBillStage(BillStage billStage) {
        this.billStage = billStage;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
