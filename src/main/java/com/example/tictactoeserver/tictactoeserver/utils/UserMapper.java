package com.example.tictactoeserver.tictactoeserver.utils;


import com.example.tictactoeserver.tictactoeserver.dto.UserResponseDTO;
import com.example.tictactoeserver.tictactoeserver.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);

    List<UserResponseDTO> toUserResponseDTOs(List<User> users);

    User toUser(UserResponseDTO userResponseDTO);

    List<User> toUsers(List<UserResponseDTO> userResponseDTOs);

}
