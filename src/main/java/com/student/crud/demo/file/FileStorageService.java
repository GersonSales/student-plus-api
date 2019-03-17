package com.student.crud.demo.file;

import com.student.crud.demo.contract.Modelable;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FileStorageService {
  String store(final MultipartFile file, final Modelable modelable);
  Resource loadAsResource(final String fileName);
}
