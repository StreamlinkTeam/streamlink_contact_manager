package cl.streamlink.contact.web.dto;

import cl.streamlink.contact.domain.Resource;

public class TimeMonthDTO {
    Resource resource;
    long year;
    long month;
    long total;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TimeMonthDTO() {
    }

    public TimeMonthDTO(Resource resource, long year, long month, long total) {
        this.resource = resource;
        this.year = year;
        this.month = month;
        this.total = total;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
