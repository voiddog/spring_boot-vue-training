package org.voiddog.spring.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.voiddog.spring.test.model.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadController {

    private static Logger logger = LoggerFactory.getLogger("Download");

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@RequestParam("name") String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return ResponseEntity.notFound().build();
        }
        File file = new File(Constants.getDownloadPath(), filePath);
        logger.info("download file name: " + filePath);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                "attachment;fileName=" + filePath);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
