package com.cms.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 25.07.2019
 */

@XmlRootElement(name = "error")
public class ErrorDetails {

    private List<String> details;
    private String message;
    private Date timeStamp;

    public ErrorDetails(List<String> details, String message, Date timeStamp) {
        this.setDetails(details);
        this.message = message;
        this.timeStamp = timeStamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
