package nl.avans.ivh11.demoapplication.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);;
    private final String DEFAULT_ERROR_VIEW = "error/error";

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ModelAndView loanNotFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error(e.getClass().getSimpleName() + ": " + e.getMessage());
        return buildModelAndView(req.getRequestURL(), e);
    }

    @ExceptionHandler({Exception.class, IOException.class})
    public ModelAndView defaultExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it. AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            logger.error("Error in defaultExceptionHandler, throwing exception");
            throw e;
        } else {
            logger.error(e.getClass().getSimpleName() + ": " + e.getMessage());
            return buildModelAndView(req.getRequestURL(), e);
        }
    }

    private ModelAndView buildModelAndView(StringBuffer url, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "Exception: " + e.getClass().getSimpleName());
        mav.addObject("lead", "There was an exception.");
        mav.addObject("message", e.getMessage());
        mav.addObject("url", url);
        mav.addObject("exception", e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}