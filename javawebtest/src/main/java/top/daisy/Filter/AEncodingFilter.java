package top.daisy.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class AEncodingFilter implements Filter {
    private static final Logger logger= LoggerFactory.getLogger(AEncodingFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("编码过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("编码过滤器处理中...");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.debug("编码过滤器完成应答");
    }

    @Override
    public void destroy() {
        logger.debug("编码过滤器销毁");

    }
}
