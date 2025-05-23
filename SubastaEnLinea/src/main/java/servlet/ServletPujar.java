package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repositorio.RepositorioException;
import subastas.modelo.Puja;
import subastas.servicio.IServicioPujas;

/**
 * Servlet implementation class ServletPujar
 */
@WebServlet("/ServletPujar")
public class ServletPujar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	IServicioPujas servicio;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPujar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String art = request.getParameter("articulo");
		String pujador = request.getParameter("user");
		String importe = request.getParameter("importe");
		Puja puja = new Puja();
		
		puja.setArticulo(art);
		puja.setFecha(LocalDate.now());
		puja.setPujador(pujador);
		puja.setImporte(Double.parseDouble(importe));
		
		try {
			servicio.pujar(puja);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
