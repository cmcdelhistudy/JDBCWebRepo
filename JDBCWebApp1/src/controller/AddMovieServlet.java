package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;

@WebServlet("/AddMovieServlet")
public class AddMovieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int i = Integer.parseInt(request.getParameter("id"));
		String t = request.getParameter("title");
		double b = Double.parseDouble(request.getParameter("budget"));
		String actors = request.getParameter("actors");

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c = DriverManager
					.getConnection("jdbc:oracle:thin:system/1267@localhost:1521:XE");

			Statement st = c.createStatement();

			int r = st.executeUpdate("insert into Movie values("+i+",'"+t+"',"+b+",'"+actors+"')");

			if (r > 0) {
				request.getRequestDispatcher("ShowMoviesInfoServlet").forward(
						request, response);
			} else {
				out.println("Movie Cannot be added ");
			}

		} catch (ClassNotFoundException e) {
			out.println("Driver Class Not Available " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("SQL Exception " + e.getMessage());
			e.printStackTrace();
		}

		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
