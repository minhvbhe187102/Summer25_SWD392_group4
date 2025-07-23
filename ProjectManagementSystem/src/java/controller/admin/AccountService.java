/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

/**
 *
 * @author Asus
 */
public class AccountService {
    private AccountDAO dao = new AccountDAO();

    public boolean createAccount(User user) {
        return dao.insert(user);
    }
}
