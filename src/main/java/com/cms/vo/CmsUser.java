package com.cms.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Inderjit SS
 * @version: v1.0.0
 * @github: https://github.com/ISingh2015/TestMongo
 * @since : 06.06.2019
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "CmsUser")
@Data
public class CmsUser {

    public static long serialVersionUID = 1l;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String _id;
    private String username;

    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String token;

    @JsonIgnore
    private String email;

    private Collection<? extends GrantedAuthority> authorities;

    public CmsUser() {
    }

    public CmsUser(String _id, String username, String password) {
        this._id = _id;
        this.username = username;
        this.password = password;
    }

    public CmsUser(String id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this._id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


}
