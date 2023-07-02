package top.daisy.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@WebFilter(filterName = "DTokenFilter", urlPatterns = "/*")
public class DTokenFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(DTokenFilter.class);
    //token请求
    private Set<String> tokenSet = new HashSet<>();
    public static final String REQUEST_TOKEN_NAME = "servlet_token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //第一步，获取请求中得token信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getParameter(REQUEST_TOKEN_NAME);
        token = token == null ? "" : token.trim();
        //第二步，验证token是否存在
        if ("".equals(token) || tokenSet.contains(token)) {
            token = UUID.randomUUID().toString();
            tokenSet.add(token);
        }
        logger.debug("token{}", token);
        request.setAttribute(REQUEST_TOKEN_NAME, token);
        //不存在就增加一个新的并添加到token列表中，返回给客户端
        //存在就直接返回token给客户端
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
