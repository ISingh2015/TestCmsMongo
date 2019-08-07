package com.cms.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 25.07.2019
 */

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "KeyValues")
@Data
public class CmsKeyValues implements Serializable, Comparable<CmsKeyValues> {

    @Transient
    public static final String SEQUENCE_NAME = "keyvalues_sequence";

    public static long serialVersionUID = 1l;
    @Id
    private Long id;

    @Field("user_id")
    @NotEmpty(message = "user_id can not be empty.")
    @Size(max = 10, message = "user_id can not be more than 10 characters.")
    private Long userId;

    @Field("key_name")
    @NotEmpty(message = "Key cannot be empty")
    @Size(max = 100, message = "key can not be more than 100 characters.")
    private String keyName;

    @Field("key_value")
    @NotEmpty(message = "Value can not be empty.")
    @Size(max = 1000, message = "Value can not be more than 1000 characters.")
    private String keyValue;

    @Override
    public int compareTo(CmsKeyValues o) {
        if (o == null) {
            return 0;
        }
        if (this.getKeyName()== o.getKeyName()) {
            return 1;
        } else if (this.getKeyName()!= o.getKeyName()) {
            return -1;
        }
        return 0;
    }

    public CmsKeyValues() {
    }

    public CmsKeyValues(String keyName, String keyValue) {
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

    public CmsKeyValues(long id, long userId, String keyName, String keyValue) {
        this.id = id;
        this.userId=userId;
        this.keyName = keyName;
        this.keyValue = keyValue;
    }

//    public String toString() {
//        return String.format("Key = '%s', Value ='%s'", getKeyName(), getKeyValue());
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getKeyName() {
//        return keyName;
//    }
//
//    public void setKeyName(String keyName) {
//        this.keyName = keyName;
//    }
//
//    public String getKeyValue() {
//        return keyValue;
//    }
//
//    public void setKeyValue(String keyValue) {
//        this.keyValue = keyValue;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
}
