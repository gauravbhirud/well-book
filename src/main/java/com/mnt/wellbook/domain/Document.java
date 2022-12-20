package com.mnt.wellbook.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mnt.wellbook.config.Constants;

import java.time.LocalDateTime;
import javax.persistence.Lob;

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
@Table(name = "tbl_document")
public class Document  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uploaded_by")
    private String uploadedBy;
    
    @Column(name = "document_name")
    private String documentName;
    
    @Column(name = "uploaded_on")
    private LocalDateTime uploadedOn;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="section_id")
    private Section section;
    
    @Column(name = "path")
    private String path;
    
    @Column(name = "remark")
    private String remark;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
    
    
    public LocalDateTime getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(LocalDateTime uploadedOn) {
        this.uploadedOn = uploadedOn;
    }
    
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    
    
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    
    public String getPath() {
        return path;
      }

      public void setPath(String path) {
        this.path = path;
      }
      
      public String getRemark() {
          return remark;
        }

        public void setRemark(String remark) {
          this.remark = remark;
        }


    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "uploadedBy='" + uploadedBy + '\'' +
            "path='" + path + '\'' +
             "remark='" + remark + '\'' +
            "}";
    }
}
