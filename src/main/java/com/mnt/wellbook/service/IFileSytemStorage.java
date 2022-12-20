package com.mnt.wellbook.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileSytemStorage {
    void init();
    String saveFile(MultipartFile file,Long secionId,String remark);
    Resource loadFile(Long Id);
}