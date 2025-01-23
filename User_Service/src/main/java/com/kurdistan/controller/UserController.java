package com.kurdistan.controller;

import com.kurdistan.dto.UserDTO;
import com.kurdistan.security.JwtUtils;
import com.kurdistan.service.interfaces.UserService;
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


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody UserDTO userDTO) {
        String token = authHeader.replace("Bearer ", "");

        // Hämta userId och email från JWT-tokenen
        String userId = jwtUtils.getUserIdFromToken(token);
        String email = jwtUtils.getEmailFromToken(token);

        // Sätt userId och email i customerDTO
        userDTO.setId(userId);
        userDTO.setEmail(email);

        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Hämta kundprofil baserat på ID från JWT
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        Optional<UserDTO> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Hämta alla kunder (för administratörer eller särskilda roller)
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> user = userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    // Uppdatera kund baserat på ID från JWT
    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateUser(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody UserDTO userDTO) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        UserDTO updatedUSer = userService.updateUser(userId, userDTO);
        return ResponseEntity.ok(updatedUSer);
    }

    // Ta bort kund baserat på ID från JWT
    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtUtils.getUserIdFromToken(token);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
