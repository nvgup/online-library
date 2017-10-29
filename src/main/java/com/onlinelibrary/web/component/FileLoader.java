package com.onlinelibrary.web.component;

import com.onlinelibrary.utils.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet("/File")
public class FileLoader extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = getDownloadedFile(request);
        downloadedFileValidation(file);
        setResponseHeader(response, file);
        setResponseContentType(response, file);
        FileUtil.writeFromFileToOutputStream(file, response.getOutputStream());
    }

    private File getDownloadedFile(HttpServletRequest request) {
        String fileName = request.getParameter("file");
        String filePath = getServletContext().getInitParameter("bookFilePath") + fileName;
        return new File(filePath);
    }

    private void downloadedFileValidation(File file) throws FileNotFoundException {
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException();
        }
    }

    private void setResponseHeader(HttpServletResponse response, File file) {
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());

        response.setHeader(headerKey, headerValue);
    }

    private void setResponseContentType(HttpServletResponse response, File file) {
        String mimeType = getServletContext().getMimeType(file.getPath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
