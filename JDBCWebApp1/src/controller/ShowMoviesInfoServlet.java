package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Movie;

@WebServlet("/ShowMoviesInfoServlet")
public class ShowMoviesInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		ArrayList<Movie> movieList = new ArrayList<Movie>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection c = DriverManager.getConnection("jdbc:oracle:thin:system/1267@localhost:1521:XE");
			Statement st = c.createStatement();

			ResultSet rs = st.executeQuery("select * from Movie ");

			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				float b = rs.getFloat(3);
				String ac = rs.getString(4);

				Movie m = new Movie();
				m.setId(id);
				m.setTitle(title);
				m.setBudget(b);
				m.setActors(ac);

				movieList.add(m);

			}

			request.setAttribute("MOVIE_LIST", movieList);
			request.getRequestDispatcher("movieList.jsp").forward(request,
					response);

		} catch (ClassNotFoundException e) {
			out.println("Driver Class Not Available " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("SQL Exception " + e.getMessage());
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
