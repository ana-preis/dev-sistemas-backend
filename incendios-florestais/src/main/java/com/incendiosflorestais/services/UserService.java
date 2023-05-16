package com.incendiosflorestais.services;

import com.incendiosflorestais.dto.UserDTO;
import com.incendiosflorestais.dto.UserForm;
import com.incendiosflorestais.models.User;
import com.incendiosflorestais.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.incendiosflorestais.dto.UserDTO.toUserDTOList;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private FireService fireService;

    @Autowired
    private PasswordEncoder encoder;

    public UserDTO save(UserForm dto) {
        User user = new User(dto);
        String password = encoder.encode(dto.password());
        user.setPassword(password);
        return new UserDTO(repository.save(user));
    }

    public UserDTO getByID(Long id) {
        Optional<User> user = repository.findById(id);
        return new UserDTO(user.get());
    }

    public List<UserDTO> search(String email, String name) {
        List<User> users;
        if(email == null && name == null) {
            users = repository.findAll();
        } else {
            users = repository.findAllByEmailOrFirstName(email, name);
        }
        return toUserDTOList(users);
    }

    public UserDTO update(Long id, UserForm dto) {
        Optional<User> userOpt = repository.findById(id);
        User user = userOpt.get();
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return new UserDTO(repository.save(user));
    }

    public void delete(Long id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) return;
        fireService.deleteUserInFireList(user.get());
        repository.delete(user.get());
    }
}
