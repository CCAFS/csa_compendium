package org.cgiar.ccafs.csa.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * This is used in production to present user appropriate error pages.
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error(HttpServletResponse response) {
        int errorCode = response.getStatus();
        if (errorCode != 401 && errorCode != 404 && errorCode != 500) errorCode = 500;
        return "/resources/" + errorCode + ".html";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
