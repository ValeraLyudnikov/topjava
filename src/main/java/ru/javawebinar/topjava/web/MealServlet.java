package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        repository = new MealRepositoryImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        if(id == null) {
            Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            LOG.info("Create {}", meal);
            repository.save(meal);
            response.sendRedirect("meals");
        } else {
            Meal mealEditing = repository.get(Integer.valueOf(id));

            if(request.getParameter("dateTime") != null) {
                mealEditing.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
            }

            if(request.getParameter("descripiton") != null) {
                mealEditing.setDescription(request.getParameter("description"));
            }

            if(request.getParameter("calories") != null) {
                mealEditing.setCalories(Integer.valueOf(request.getParameter("calories")));
            }

            LOG.info("Edit {}", mealEditing);
            response.sendRedirect("meals");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null) {
            LOG.info("getAll");
            req.setAttribute("mealList",
                    MealsUtil.getWithExceeded(repository.getAll(), 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if(action.equals("delete")) {
            int id = getId(req);
            LOG.info("Delete {}", id);
            repository.delete(id);
            resp.sendRedirect("meals");
        } else if(action.equals("create")){
            req.getRequestDispatcher("mealAdd.jsp").forward(req, resp);
        } else if(action.equals("update")) {
            int id = getId(req);
            req.setAttribute("meal",
                    repository.get(id));
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
