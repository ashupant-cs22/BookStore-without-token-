package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.LoginDto;
import com.example.bookstoreapp.Dto.UserDto;

import com.example.bookstoreapp.Exception.BookStoreExceptionMessage;
import com.example.bookstoreapp.Model.UserDetail;
import com.example.bookstoreapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {


    @Autowired
    UserRepository userRepository;


    public UserDetail login(LoginDto loginDto){
        Optional<UserDetail>userDetail=userRepository.findByEmail(loginDto.getEmail());
        if(loginDto.getEmail().equals(userDetail.get().getEmail())&&loginDto.getPassword().equals(userDetail.get().getPassword())){
            return userDetail.get();
        }else {
            throw new BookStoreExceptionMessage("Wrong Email Or Password");
        }
    }


    @Override
    public UserDetail createUser(UserDto userDto) {
        List<UserDetail> userDetails = userRepository.findAll();
        for (UserDetail u : userDetails) {
            if (u.getFullname().equals(userDto.getFullname()) || u.getEmail().equals(userDto.getEmail())) {
                throw (new BookStoreExceptionMessage("Please enter different name"));
            }
        }
       UserDetail userDetail1 =new UserDetail(userDto);
        userRepository.save(userDetail1);
        return userDetail1;
    }
    @Override
    public UserDetail getIdOfUser(int Id) {
        return userRepository.findById(Id)
                .orElseThrow(()->new BookStoreExceptionMessage("user not found"));
    }
    @Override
    public List<UserDetail> getListOfUser(){
        List<UserDetail>userDetails=userRepository.findAll();
        return userDetails;
    }

    @Override
    public Object updateUserData(int id, UserDto userDTO) {
        if (userRepository.findById(id).isPresent()) {
            List<UserDetail> userDetails = userRepository.findAll();
            for (UserDetail u : userDetails) {
                if (u.getFullname().equals(userDTO.getFullname())) {
                    return "name already present";
                }
            }
        }else throw (new BookStoreExceptionMessage("id not found"));
        UserDetail userDetail=new UserDetail(userDTO);
            userDetail.setUserId(id);
            userRepository.save(userDetail);
            return userDetail;

    }
    @Override
    public void deleteUser(Integer id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);

        }else throw (new BookStoreExceptionMessage("id not found"));

    }


}


