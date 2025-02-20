package com.kurdistan.controller;

import com.kurdistan.dto.UserDTO;
import com.kurdistan.security.JwtUtils;
import com.kurdistan.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Hämta kundprofil baserat på ID från JWT
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        Optional<UserDTO> user = userService.getUserById(userId);
        System.out.println(user);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Uppdatera kund baserat på ID från JWT
    @PutMapping("/edit")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody UserDTO userDTO) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        UserDTO updatedUSer = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUSer);
    }

    // Ta bort kund baserat på ID från JWT
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
