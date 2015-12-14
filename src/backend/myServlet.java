package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class myServlet
 */
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("usr"));
		System.out.println(request.getParameter("pwd"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubes
		
		List<FileItem> items;
		String fileName = "";
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) {
			    if (item.isFormField()) {
			        // Process normal fields here.
			        System.out.println("Field name: " + item.getFieldName());
			        System.out.println("Field value: " + item.getString());
			    } else {
			        // Process <input type="file"> here.
			        System.out.println("Field name: " + item.getFieldName());
			        System.out.println("Field value (file name): " + item.getName());
			        fileName = item.getName();
			    }            
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter writer = response.getWriter();
			writer.append("error");
			response.flushBuffer();
			return;
		}
//		response.sendRedirect("dropbox.jsp");
		PrintWriter writer = response.getWriter();
		writer.append(fileName);
		response.flushBuffer();
	}
	
}
