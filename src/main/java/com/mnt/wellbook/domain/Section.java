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
@Table(name = "tbl_section")
public class Section  {

    private static final long serialVersionUID = 1L;
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client client;
    
    
    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    // prettier-ignore
    @Override
    public String toString() {
        return "Section{" +
            "name='" + name + '\'' +
            "description='" + description + '\'' +
            "}";
    }
}
