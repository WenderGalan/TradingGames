package com.tradinggames.api.controllers;

import com.tradinggames.api.models.UserModel;
import com.tradinggames.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Classe de controle para funções do usuário
 *
 * @author joao
 * @version 0.1
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder crypt;

    @Autowired
    private UserRepository userRepository;

    public UserController() {

    }

    @GetMapping("")
    public String welcomeToUserPage() {
        return "I told you. This is MY world !";
    }

    @PostMapping("/register")
    private UserModel registerUser(@RequestBody(required = true) UserModel requestUser) {

        UserModel user = new UserModel();

        user.setUserName(requestUser.getUserName());
        user.setUserEmail(requestUser.getUserEmail());
        user.setUserPassword(crypt.encode(requestUser.getUserPassword()));

        try {
            return userRepository.save(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
//			return "duplicated";
            throw new RuntimeException("Usuário duplicado");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/registerInUrl")
    private UserModel registerUserInUrl(
            @RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail,
            @RequestParam("userPassword") String userPassword
    ) {

        UserModel user = new UserModel();

        user.setUserName(userName);
        user.setUserEmail(userEmail);
        user.setUserPassword(crypt.encode(userPassword));

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Efetua o login de um usuário já registrado
    @PostMapping("/login")
    private UserModel authUser(@RequestBody(required = true) UserModel requestUser) {
        try {
            UserModel user = userRepository.findByUserEmail(requestUser.getUserEmail());

            if (user != null && crypt.matches(requestUser.getUserPassword(), user.getUserPassword())) {
                return user;
            } else {
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para verificar se determinado usuário já existe no banco de dados
    public boolean userEmailExists(String email) {
        return userRepository.findByUserEmail(email) != null ? true : false;
    }

    // Utiliza o email de um usuário para retornar seu Id
    public String getUserIdByEmail(String email) {
        return userRepository.findByUserEmail(email).getUserId();
    }
}

