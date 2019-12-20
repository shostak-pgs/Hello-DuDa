package app.servlets;

import app.page_path.PagePath;
import app.entity.Basket;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoodsAddServlet extends HttpServlet {
    private static final String ITEM = "good";
    private static final String EMPTY_ELEMENT = "--Choose item--";
    public static final String BASKET = "order";

    /**
     * Handles {@link HttpServlet} POST Method.
     * If the item has not been selected returns to the selection page. If selected - adds it to
     * the basket and returns to the selection page
     * @param request  the {@link HttpServletRequest} contains selected item as a parameter
     * @param response the {@link HttpServletResponse}
     * @throws IOException      thrown when occur exception in getting Writer object
     * @throws ServletException thrown when occur exception in redirecting
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        String chosenItem = request.getParameter(ITEM);
        request.getSession().setAttribute(BASKET, Basket.getBasket());

        putToBasket(chosenItem);
        forwardTo(request, response, PagePath.ADD);
    }

    /**
     * Redirect request to the transferred path
     * @param request  the {@link HttpServletRequest} contains user name and map for containing order
     * @param response the {@link HttpServletResponse}
     * @param path     the path for redirection
     * @throws IOException      thrown when occur exception in redirecting
     * @throws ServletException thrown when occur exception in redirecting
     */
    private void forwardTo(final HttpServletRequest request, final HttpServletResponse response, PagePath path) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path.getPath());
        requestDispatcher.forward(request, response);
    }

    /**
     * Add not empty goods to basket
     * @param chosenItem item for adding
     */
    public void putToBasket(final String chosenItem) {
        if (!(chosenItem == null | (chosenItem.equals(EMPTY_ELEMENT)))) {
            Basket.getBasket().toBasket(chosenItem);
        }
    }
}


