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

public class GoodsAddServlet extends HttpServlet {
    private static final String GOOD = "good";
    private static final String FIRST_CHOISE = "WEB-INF/view/firstSelect.jsp";
    private static final String NEXT_CHOISE = "WEB-INF/view/addSelect.jsp";

    /**
     * Handles {@link HttpServlet} POST Method.
     * If the item has not been selected returns to the selection page. If selected - adds it to
     * the order and returns to the selection page
     * @param request  the {@link HttpServletRequest} contains selected item as a parameter
     * @param response the {@link HttpServletResponse}
     * @throws IOException thrown when occur exception in getting Writer object
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher;
        String chosenItem = request.getParameter(GOOD);
        Map<String, Double> orderMap = (HashMap<String, Double>) request.getSession().getAttribute(ORDER);

        if (chosenItem == null) {
            if (orderMap.size() == 0) {
                requestDispatcher = request.getRequestDispatcher(FIRST_CHOISE);
            }
            requestDispatcher = request.getRequestDispatcher(NEXT_CHOISE);
        } else {

            requestDispatcher = request.getRequestDispatcher(NEXT_CHOISE);
            GoodsUtil.toBasket(chosenItem, orderMap);
        }


        requestDispatcher.forward(request, response);
    }
}


