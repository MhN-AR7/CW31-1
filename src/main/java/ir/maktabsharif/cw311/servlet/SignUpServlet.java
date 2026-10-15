package ir.maktabsharif.cw311.servlet;

import com.fasterxml.jackson.core.JsonParseException;
import ir.maktabsharif.cw311.dto.UserReqDTO;
import ir.maktabsharif.cw311.dto.UserRespDTO;
import ir.maktabsharif.cw311.exception.BusinessException;
import ir.maktabsharif.cw311.service.UserService;
import ir.maktabsharif.cw311.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@WebServlet(name = "signUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().equalsIgnoreCase("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Content type is wrong!");
            return;
        }

        UserReqDTO userReqDTO = null;

        try {
            userReqDTO = objectMapper.readValue(req.getReader(), UserReqDTO.class);
        }
        catch (JsonParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Json Body!");
        }

        UserRespDTO userRespDTO = null;

        try {
            userRespDTO = userService.register(userReqDTO);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), userRespDTO);
        } catch (BusinessException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
