package com.mnt.wellbook.service;

import java.io.IOException;
import java.util.stream.Stream;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import java.nio.file.StandardCopyOption;

import com.mnt.wellbook.domain.Document;
import com.mnt.wellbook.domain.Section;
import com.mnt.wellbook.repository.FileRepository;

import org.springframework.core.io.Resource;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mnt.wellbook.config.FileUploadProperties;
import javax.annotation.PostConstruct;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileStorageService implements IFileSytemStorage{

	
  private FileRepository fileDBRepository;
  
  private final Path dirLocation;

  
  @Autowired	
  public FileStorageService(FileUploadProperties fileUploadProperties,FileRepository fileDBRepository) {
      this.dirLocation = Paths.get(fileUploadProperties.getLocation()).toAbsolutePath().normalize();
      this.fileDBRepository=fileDBRepository;
  }
  
  @Override
  @PostConstruct
  public void init() {
      // TODO Auto-generated method stub
      try {
          Files.createDirectories(this.dirLocation);
      } 
      catch (Exception ex) {
//          throw new FileStorageException("Could not create upload dir!");
      }
  }

  
  @Override
  public String saveFile(MultipartFile file,Long sectionId,String remark) {
      
	  try {
          String fileName = file.getOriginalFilename();
          Path dfile = this.dirLocation.resolve(fileName);
          Files.copy(file.getInputStream(), dfile,StandardCopyOption.REPLACE_EXISTING);
          
          Document document = new Document();
          
          Section section=new Section();
          section.setSectionId(sectionId);
          document.setSection(section);
          document.setUploadedOn(LocalDateTime.now());
          document.setUploadedBy("Admin");
          document.setDocumentName(fileName);
          document.setPath(dfile.toString());
          document.setRemark(remark);
          
          fileDBRepository.save(document);
          
          return fileName;
	  }catch (Exception e) {
          return null;
      }  
  }

  @Override
  public Resource loadFile(Long id) {
      // TODO Auto-generated method stub
      try {
    	  Document document=  fileDBRepository.findById(id).get();
        Path file = this.dirLocation.resolve(document.getDocumentName()).normalize();
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } 
        else {
            return null;
        }
      } 
      catch (MalformedURLException e) {
          return null;
      }           
  }
}