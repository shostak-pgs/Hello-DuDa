package app.servlets;

import app.service.GoodsUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static app.servlets.TermsCheckServlet.ORDER;

public class CompletiveServlet extends HttpServlet {
    private static final String GOOD = "good";
    private static final String EMPTY_BASKET_PATH = "WEB-INF/view/emptyBasketError.jsp";
    private static final String PRINT_CHECK_PATH = "/printCheckServlet";

    /**
     * Handles {@link HttpServlet} POST Method. Called if the submit button was clicked.
     * If a product has been selected before, it adds it to the basket and redirects to the check print servlet.
     * If no products have been selected, a warning page is displayed.
     * @param request  the {@link HttpServletRequest} may contain the last selected item
     * @param response the {@link HttpServletResponse}
     * @throws IOException thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher;

        Map<String, Double> orderMap = (HashMap<String, Double>) request.getSession().getAttribute(ORDER);
        String chosenItem = request.getParameter(GOOD);

        if(chosenItem != null) {
            GoodsUtil.toBasket(chosenItem, orderMap);
        }

        if (orderMap.size() == 0) {
            requestDispatcher = request.getRequestDispatcher(EMPTY_BASKET_PATH);
        }
        requestDispatcher = request.getRequestDispatcher(PRINT_CHECK_PATH);

        requestDispatcher.forward(request, response);
    }
}
