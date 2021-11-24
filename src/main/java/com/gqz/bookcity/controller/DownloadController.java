package com.gqz.bookcity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>下载文件</p>
 *
 * @author gqz20
 * @create 2021-11-16 09:18
 **/
@RequestMapping("down")
@RestController
public class DownloadController {
    /**
     * 文件的下载
     *
     * @param request
     * @param response
     * @param filePath
     */
    @GetMapping("file")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath) {

        File file = new File(filePath);
        String filenames = file.getName();
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filenames.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);
            os.flush();
            os.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
