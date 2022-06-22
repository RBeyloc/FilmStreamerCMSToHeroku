package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserRestController {

    @Autowired
    UserService userService;

    //here we are creating our end-point rest API
    //CRUD: read all movies
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Optional<Iterable<User>> usersRetrieved = userService.getAllUsers();

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "users");

        if (usersRetrieved.isPresent()) {
            headers.add("operationStatus", "success");
            return ResponseEntity.accepted().headers(headers).body(usersRetrieved.get());
        } else {
            headers.add("operationStatus", "fail");
            return ResponseEntity.accepted().headers(headers).body(null);
        }
    }
}
