package app.servlets;

import app.service.GoodsUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TermsCheckServlet extends HttpServlet {
    private static final String USER_NAME = "name";
    private static final String TERM = "term";
    public static final String ALL_GOODS_LIST = "allGoodsList";
    public static final String ORDER = "order";
    private static final String REDIRECT_PATH = "WEB-INF/view/termsError.jsp";
    private static final String FORWARD_PATH = "WEB-INF/view/firstSelect.jsp";

    private Map<String, Double> goodsMap;

    /**
     * Called by the servlet container to indicate to a servlet that the servlet is being placed
     * into service. It receives ServletConfig object from the servlet container for getting parameters.
     * @param config the <code>ServletConfig</code> object that contains configuration
     * information for this servlet
     * @throws ServletException if an exception occurs interrupts the servlet's normal operation
     */
    @Override
    public void init(final ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        goodsMap = GoodsUtil.buildGoodsMap(servletContext);
        servletContext.setAttribute(ALL_GOODS_LIST, goodsMap);
        super.init(config);
    }

    /**
     * Handles {@link HttpServlet} POST Method. Creates an HTML page containing a greeting and
     * inviting the user to select products. In this method user's name sets as a session attribute
     * @param request the {@link HttpServletRequest} contains user name as a parameter. User name
     * transferred from the start(default) HTML page
     * @param response the {@link HttpServletResponse}
     * @throws IOException thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {

        String userName = request.getParameter(USER_NAME);
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_NAME, userName);

        if (request.getParameter(TERM) == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(REDIRECT_PATH);
            requestDispatcher.forward(request, response);
        }
        Map<String, Double> order = new HashMap<>();
            session.setAttribute(ORDER, order);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(FORWARD_PATH);
            requestDispatcher.forward(request, response);
        }
}
