package app.filters;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.Filter;

public class CheckButtonFilter implements Filter {
    private static final String SUBMIT = "submit";
    private static final String PUSHED_BUTTON = "button";
    private static final String REDIRECT_PATH = "/complete";

    private FilterConfig filterConfig;

    public void init(FilterConfig config) {
        this.filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String pushedButton = request.getParameter(PUSHED_BUTTON);

        if (pushedButton.equalsIgnoreCase(SUBMIT)) {
            request.getRequestDispatcher(REDIRECT_PATH).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
