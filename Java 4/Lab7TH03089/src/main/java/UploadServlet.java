import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.Paths;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5)

public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/bai2.jsp").forward(req, resp);
    }

    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileType = filePart.getContentType();
        long fileSize = filePart.getSize();

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadPath + File.separator + fileName);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.println("{");
        out.println("\"name\": \"" + fileName + "\",");
        out.println("\"type\": \"" + fileType + "\",");
        out.println("\"size\": " + fileSize);
        out.println("}");
    }

}
