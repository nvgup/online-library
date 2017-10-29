package com.onlinelibrary.web.component;

import com.onlinelibrary.utils.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@WebServlet("/Image")
public class ImageLoader extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        File file = getDownloadedImage(request);
        FileUtil.writeFromFileToOutputStream(file, response.getOutputStream());
    }

    private File getDownloadedImage(HttpServletRequest request) {
        String image = request.getParameter("image");
        String imagePath = getServletContext().getInitParameter("imagePath") + image;
        File file = new File(imagePath);
        if (!file.exists() || file.isDirectory()) {
            imagePath = getServletContext().getInitParameter("imagePath")
                    + getServletContext().getInitParameter("defaultImage");
            file = new File(imagePath);
        }
        return file;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
