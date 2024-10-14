package com.example.myproject.serviceImpl;

import com.example.myproject.service.UserService;
import com.example.myproject.dto.UserDTO;
import com.example.myproject.model.User;
import com.example.myproject.converter.UserConverter;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    // TODO: Add repository dependency

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        // TODO: Implement method
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // TODO: Implement method
        return null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // TODO: Implement method
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        // TODO: Implement method
    }
}