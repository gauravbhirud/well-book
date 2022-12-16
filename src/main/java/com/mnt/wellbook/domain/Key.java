package com.mnt.wellbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnt.wellbook.config.Constants;
import java.time.LocalDateTime;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "tbl_key")
public class Key  {

    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alphanumeric_key")
    private String alphanumericKey;
    
    @Column(name = "key_type")
    private String keyType;
    
    @Column(name = "create_by")
    private String createBy;
    
    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;
    
    @Column(name = "used_status")
    private String usedStatus;
    
    @Column(name = "email")
    private String email;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAlphanumericKey() {
        return alphanumericKey;
    }

    public void setAlphanumericKey(String alphanumericKey) {
        this.alphanumericKey = alphanumericKey;
    }
    
    
    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }
    
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    
    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }
	
    public String getUsedStatus() {
        return usedStatus;
    }

    public void setUsedStatus(String usedStatus) {
        this.usedStatus = usedStatus;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    


    // prettier-ignore
    @Override
    public String toString() {
        return "Key{" +
            "keyType='" + keyType + '\'' +
            "usedStatus='" + usedStatus + '\'' +
            "}";
    }
}
