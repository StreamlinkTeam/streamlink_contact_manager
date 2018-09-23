package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.utils.enums.SocietyActivityArea;
import cl.streamlink.contact.utils.enums.SocietyStage;

import java.util.ArrayList;
import java.util.List;

public class SocietyDTO extends AbstractProfilDTO {

    private String label;

    private Integer staffNumber;

    private String SupplierNumber;

    private SocietyStage stage;

    private SocietyActivityArea activityArea;

    private List<String> services = new ArrayList<String>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getSupplierNumber() {
        return SupplierNumber;
    }

    public void setSupplierNumber(String supplierNumber) {
        SupplierNumber = supplierNumber;
    }

    public SocietyStage getStage() {
        return stage;
    }

    public void setStage(SocietyStage stage) {
        this.stage = stage;
    }

    public SocietyActivityArea getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(SocietyActivityArea activityArea) {
        this.activityArea = activityArea;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
