package com.student.crud.demo.file;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class Util {

  public static ResponseEntity<Resource> getPreparedResponseEntity(final HttpServletRequest request,
                                                             final Resource resource) {
    String filename = resource.getFilename();
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(getFileContent(request, resource)))
        .header("Content-Name", filename)
        .body(resource);
  }

  private static String getFileContent(final HttpServletRequest request, final Resource resource) {
    String contentType;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }

    if (contentType == null) {
      contentType = "application/octet-stream";
    }
    return contentType;
  }
}
