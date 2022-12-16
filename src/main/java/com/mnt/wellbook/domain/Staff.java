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
@Table(name = "tbl_staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Staff  {

    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private Long phone;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;
    
    @Column(name = "create_datetime")
    private LocalDateTime createDatetime;
    
    @Column(name = "update_datetime")
    private LocalDateTime updateDatetime;
    
    @Column(name = "register_status")
    private String registerStatus;
    
    @Column(name = "active_status")
    private String activeStatus;
    
    @Column(name = "role")
    private String role;
    
    
    @Column(name = "testing")
    private String testing;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public LocalDateTime getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(LocalDateTime createDatetime) {
        this.createDatetime = createDatetime;
    }
    
    public LocalDateTime getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(LocalDateTime updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
    
    public String getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(String registerStatus) {
        this.registerStatus = registerStatus;
    }
    
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    


    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "firstname='" + firstName + '\'' +
            "lastname='" + lastName + '\'' +
            "email='" + email + '\'' +
            "phone='" + phone.toString() + '\'' +
            "}";
    }
}
