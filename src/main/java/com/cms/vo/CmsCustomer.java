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
import java.util.Date;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "Customer")
@Data
public class CmsCustomer implements Serializable, Comparable<CmsCustomer> {

    public static long serialVersionUID = 1l;

    @Transient
    public static final String SEQUENCE_NAME = "customers_sequence";

    @Id
    private Long id;

    @Field("user_id")
    @NotEmpty(message = "user_id can not be empty.")
    @Size(max = 10, message = "user_id can not be more than 10 characters.")
    private Long userId;

    @Field("first_name")
    @NotEmpty(message = "first_Name can not be empty.")
    @Size(max = 100, message = "first_Name can not be more than 100 characters.")
    private String firstName;

    @Field("last_name")
    @NotEmpty(message = "last_Name can not be empty.")
    @Size(max = 100, message = "last_Name can not be more than 100 characters.")
    private String lastName;

    @Field("address")
    @NotEmpty(message = "address can not be empty.")
    @Size(max = 500, message = "address can not be more than 500 characters.")
    private String address;

    @Field("pinCode")
    @NotEmpty(message = "pinCode can not be empty.")
    @Size(max = 10, message = "pinCode can not be more than 10 digits.")
    private Long pinCode;

    @Field("email")
    @NotEmpty(message = "email can not be empty.")
    @Size(max = 50, message = "email can not be more than 50 characters")
    private String email;

    @Field("userName")
    @NotEmpty(message = "CmsCustomer Login Name can not be empty.")
    @Size(max = 50, message = "CmsCustomer Login Name can not be more than 15 characters")
    private String userName;

    @Field("userPassword")
    @NotEmpty(message = "CmsCustomer Login Pass can not be empty.")
    @Size(max = 50, message = "CmsCustomer Login Pass can not be more than 35 characters")
    private String userPassword;

    @Field("mobileNo")
    @NotEmpty(message = "mobileNO can not be empty.")
    @Size(max = 10, message = "mobileNO can not be more than 10 digits.")
    private Long mobileNo;

    @Field("userRole")
    @NotEmpty(message = "CmsCustomer Login Role can not be empty.")
    @Size(max = 25, message = "CmsCustomer Login Role can not be more than 25 characters")
    private String userRole;

    @Field("created")
    private Long created = (new Date().getTime()) / 1000;

    @Field("modified")
    private Long modified = (new Date().getTime()) / 1000;

    public CmsCustomer() {
    }

    public CmsCustomer(Long custId, Long userId, String firstName, String lastName, String address, Long pinCode, String email, Long mobileNo, String userName, String password) {
        this.setId(custId);
        this.setUserId(userId);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setPinCode(pinCode);
        this.setEmail(email);
        this.setMobileNo(mobileNo);
        this.setUserName(userName);
        this.setUserPassword(password);
    }

    public CmsCustomer(String firstName, String lastName, String address, Long pinCode, String email, Long mobileNo, String userName, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setPinCode(pinCode);
        this.setEmail(email);
        this.setMobileNo(mobileNo);
        this.setUserName(userName);
        this.setUserPassword(password);

    }

    /**
     * @return Formatted String with Instance Values
     */
    @Override
    public String toString() {
        return String.format(
                "id=%s, firstName='%s', lastName='%s', address='%s',pincode='%s','email=%s',mobileNo='%s',userName='%s',userRole='%s'",
                getId(), getFirstName(), getLastName(), getAddress(), getPinCode(), getEmail(), getMobileNo(), getUserName(),getUserRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int hasCode() {
        return (int) (getFirstName() + getLastName()).hashCode();
    }

    public int compareTo(CmsCustomer o) {
        if (o == null) {
            return 0;
        }
        if (this.getFirstName()== o.getFirstName()) {
            return 1;
        } else if (this.getFirstName() != o.getFirstName()) {
            return -1;
        }
        return 0;
    }

    public boolean validCustomer() {
        boolean validCustomer=true;
        if (getFirstName().isEmpty() && getLastName().isEmpty() && getEmail().isEmpty() && getAddress().isEmpty() && getPinCode()==0 && getMobileNo()==0 && getUserName().isEmpty() && getUserPassword().isEmpty() && getUserRole().isEmpty()) {
            validCustomer=false;
        }
        return validCustomer;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Long getPinCode() {
//        return pinCode;
//    }
//
//    public void setPinCode(Long pinCode) {
//        this.pinCode = pinCode;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getUserPassword() {
//        return userPassword;
//    }
//
//    public void setUserPassword(String userPassword) {
//        this.userPassword = userPassword;
//    }
//
//    public Long getMobileNo() {
//        return mobileNo;
//    }
//
//    public void setMobileNo(Long mobileNo) {
//        this.mobileNo = mobileNo;
//    }
//
//    public String getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(String userRole) {
//        this.userRole = userRole;
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

