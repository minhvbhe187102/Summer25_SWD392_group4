/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

/**
 *
 * @author Asus
 */
@WebServlet("/createAccount")
public class AccountController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        User newUser = new User(email, fullName, role, password);
        AccountService service = new AccountService();
        boolean result = service.createAccount(newUser);

        if (result) {
            request.setAttribute("message", "Account Created Successfully");
        } else {
            request.setAttribute("message", "Error creating account");
        }
        request.getRequestDispatcher("jsp.admin/createAccount.jsp").forward(request, response);
    }
}
