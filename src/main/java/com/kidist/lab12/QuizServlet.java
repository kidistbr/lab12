package com.kidist.lab12;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "QuizServlet", value = "/QuizServlet")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession s = request.getSession();
        int answer = Integer.parseInt(request.getParameter("result"));
        Quiz quiz;

        if (s.getAttribute("quiz") == null || answer == 0) s.setAttribute("quiz", new Quiz());
        quiz = (Quiz) s.getAttribute("quiz");
        if (answer != 0 && quiz.getCurrent() < 5) quiz.next(answer, quiz.getCurrent());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>Quiz</title></head><body>" +
                "<h1>The Number Quiz</h1>\n");
        if (quiz.getCurrent() < 5)
            out.print("<p> Don't give up. You are on question " + (quiz.getCurrent() + 1) + "/5</p>\n" +
                    "<p> Your current score: " + quiz.getScore() + "</p>\n" +
                    "<p>Can you guess the next number in the sequence?</p>\n" +
                    "<p> " + quiz.getQuestion(quiz.getCurrent()) + "</p>" +
                    "<form action=\"QuizServlet\" method=\"post\">\n" +
                    "   <p>Your Answer: <input type=\"number\" name=\"result\" min=1 max=99999 required/></p>\n" +
                    "   <input type=\"submit\"/>\n" +
                    "</form>" +
                    "</body></html>");
        else {
            int score = quiz.getScore();
            s.removeAttribute("quiz");
            out.print("<p>The quiz is over. Your final score is " + score + "/5. Try again?</p>" +
                    "<form action=\"QuizServlet\" method=\"post\">\n" +
                    "   <input type=\"hidden\" name=\"result\" value=\"0\"/>\n" +
                    "   <input type=\"submit\"/>\n" +
                    "</form>" + "</body></html>");
        }
    }
}
