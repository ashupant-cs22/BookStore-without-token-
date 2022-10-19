package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.LoginDto;
import com.example.bookstoreapp.Dto.UserDto;
import com.example.bookstoreapp.Model.UserDetail;
import java.util.List;

public interface IUserService {


    UserDetail createUser(UserDto userDto);

    UserDetail getIdOfUser(int Id);

    Object updateUserData(int id, UserDto userDTO);

    void deleteUser(Integer id);

    List<UserDetail> getListOfUser();

    UserDetail login(LoginDto loginDto);
}
