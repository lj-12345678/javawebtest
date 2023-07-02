package top.daisy.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.daisy.Filter.DTokenFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "indexServlet", urlPatterns = "/index.demo")

public class indexServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(indexServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("indexServlet处理应答中");
        String token = (String) req.getAttribute(DTokenFilter.REQUEST_TOKEN_NAME);
        resp.setContentType("text/html");
        resp.getWriter().println(String.format("<h1>欢迎：%s,token:%s</h1>", req.getParameter("name"), token));

    }
}
