package com.example.restapigit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.restapigit.controller.UserController;
import com.example.restapigit.entity.User;
import com.example.restapigit.repository.UserRepository;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testCreateUser_success() throws Exception {
        User user = new User();
        user.setName("Rajanichirala");
        user.setEmail("rajanibangaru@gmail.com");
        user.setPhone("9347319203");

        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Rajanichirala",
                      "email": "rajanibangaru@gmail.com",
                      "phone": "9347319203"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rajanichirala"))
                .andExpect(jsonPath("$.email").value("rajanibangaru@gmail.com"))
                .andExpect(jsonPath("$.phone").value("9347319203"));
    }

    @Test
    void testUpdateUser_success() throws Exception {
        User existingUser = new User();
        existingUser.setUserId(5L);
        existingUser.setName("Rajani");
        existingUser.setEmail("rajanibangaru@gmail.com");
        existingUser.setPhone("9347319203");

        when(userRepository.findById(5L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        mockMvc.perform(put("/api/users/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "name": "Rajanichirala",
                      "email": "rajanibangaru@gmail.com",
                      "phone": "9347319203"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rajanichirala"));
    }

    @Test
    void testDeleteUser_success() throws Exception {
        when(userRepository.existsById(5L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(5L);

        mockMvc.perform(delete("/api/users/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteUser_notFound() throws Exception {
        when(userRepository.existsById(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/users/99"))
                .andExpect(status().isNotFound());
    }
}
