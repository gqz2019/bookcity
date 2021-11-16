package com.gqz.bookcity.controller;

import com.gqz.bookcity.constant.StatusCode;
import com.gqz.bookcity.entity.Result;
import com.gqz.bookcity.pojo.FastDFSFile;
import com.gqz.bookcity.service.FileService;
import com.gqz.bookcity.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-10-28 15:58
 **/
@RestController
@CrossOrigin
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) Integer id) throws IOException {
        FastDFSFile fastDFSFile = new FastDFSFile();

        fastDFSFile.setName(file.getOriginalFilename());
        fastDFSFile.setContent(file.getBytes());
        fastDFSFile.setExt(
                fileExtension(file)
        );

        String[] uploads = FastDFSClient.upload(fastDFSFile);

        String s = FastDFSClient.getTrackerUrl() + "/" + uploads[0] + "/" + uploads[1];
        fileService.save(s, id);
        return new Result<>(true, StatusCode.OK, "上传成功", s);
    }

    private static String fileExtension(MultipartFile file) {
        int index = file.getOriginalFilename().lastIndexOf(".");
        return file.getOriginalFilename().substring(index + 1).toLowerCase();
    }
}