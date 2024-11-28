package com.example.tictactoeserver.tictactoeserver.service;


import com.example.tictactoeserver.tictactoeserver.dto.RegistrationRequestDTO;
import com.example.tictactoeserver.tictactoeserver.dto.UserResponseDTO;
import com.example.tictactoeserver.tictactoeserver.exception.ElementAlreadyExistsException;
import com.example.tictactoeserver.tictactoeserver.exception.InValidElementException;
import com.example.tictactoeserver.tictactoeserver.exception.NoSuchElementExistsException;
import com.example.tictactoeserver.tictactoeserver.model.User;
import com.example.tictactoeserver.tictactoeserver.model.UserRole;
import com.example.tictactoeserver.tictactoeserver.repo.UserRepo;
import com.example.tictactoeserver.tictactoeserver.utils.ValidationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ValidationUtilities validationUtilities;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getUser() {
        User user = getPrincipalUser();

        if (user == null)
            throw new NoSuchElementException(
                    "User Not Found!!");
        return user;
    }

    @Override
    public UserResponseDTO updateUserProfile(RegistrationRequestDTO registrationRequest) {
        User principalUser = getPrincipalUser();

        if (principalUser == null)
            throw new NoSuchElementExistsException("User Not Found!");

        validateUser(registrationRequest);

        if (principalUser.getMobile() != null
                && !principalUser.getMobile().equals(registrationRequest.getMobile())) {
            if (userRepo.existsByMobile(registrationRequest.getMobile()))
                throw new ElementAlreadyExistsException(
                        "Mobile number already registered!!");
            else
                principalUser.setIsMobileVerified(false);
        }
        if (principalUser.getEmail() != null
                && principalUser.getEmail().equals(registrationRequest.getEmail())) {
            if (userRepo.existsByMobile(registrationRequest.getEmail()))
                throw new ElementAlreadyExistsException(
                        "Email address already registered!!");
            else
                principalUser.setIsEmailVerified(false);
        }

        principalUser.setFirstName(registrationRequest.getFirstName());
        principalUser.setLastName(registrationRequest.getLastName());
        principalUser.setEmail(registrationRequest.getEmail());
        principalUser.setMobile(registrationRequest.getMobile());
        principalUser.setEmail(registrationRequest.getEmail());
        principalUser.setRole(UserRole.ROLE_USER); // Default role

        User savedUser = userRepo.save(principalUser);

        return new UserResponseDTO(
                savedUser.getUserId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getMobile(),
                savedUser.getRole()
        );
    }

    @Override
    public String deleteUser() {
        User user = getPrincipalUser();
        if (user == null)
            throw new NoSuchElementExistsException("User Not Found!!");

        userRepo.delete(user);
        return "User deleted successfully";
    }

    private void validateUser(RegistrationRequestDTO user) {
        if(user.getFirstName() == null || user.getFirstName().isEmpty())
        {
            throw new InValidElementException("First Name must be provided!");
        }
        if (!validationUtilities.isValidName(user.getFirstName())) {
            throw new InValidElementException(
                    "First name must only contains alphabets. Invalid entry - {" + user.getFirstName() + "}");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty())
        {
            throw new InValidElementException("Last Name must be provided!");
        }
        if (!validationUtilities.isValidName(user.getLastName())) {
            throw new InValidElementException(
                    "Last name must only contains alphabets. Invalid entry - {" + user.getLastName() + "}");
        }
        if(user.getMobile() == null || user.getMobile().isEmpty())
        {
            throw new InValidElementException("Mobile number must be provided!");
        }
        if (!validationUtilities.isValidMobileNumber(user.getMobile())) {
            throw new InValidElementException(
                    "Mobile number must only contain 10 digits. Invalid entry - {" + user.getMobile() + "}");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty())
        {
            throw new InValidElementException("Email must be provided!");
        }
        if (!validationUtilities.isValidEmailAddress(user.getEmail())) {
            throw new InValidElementException(
                    "Email address must only contain 10 digits. Invalid entry - {" + user.getEmail() + "}");
        }
    }

    @Override
    public Boolean isAuthenticated()
    {
        return getPrincipalUser() != null;
    }

    private User getPrincipalUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // fetch username
        return userRepo.findByEmail(username); // fetch user based on JWT token details
    }

    @Override
    public User loadUserByUsername(String email) {
        return userRepo.findByEmail(email);
    }


}
