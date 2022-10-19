package com.example.bookstoreapp.Controller;


import com.example.bookstoreapp.Dto.LoginDto;
import com.example.bookstoreapp.Dto.ResponseDTO;
import com.example.bookstoreapp.Dto.UserDto;
import com.example.bookstoreapp.Model.UserDetail;
import com.example.bookstoreapp.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO>loginUser(@RequestBody LoginDto loginDto){
        ResponseDTO responseDTO=new ResponseDTO("Login successfull",userService.login(loginDto));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> AddBook(@Valid @RequestBody UserDto userDto) {
       userService.createUser(userDto);
        ResponseDTO responseDTO = new ResponseDTO("User has been added", userDto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDetail userDetail= userService.getIdOfUser(id);
        ResponseDTO response = new ResponseDTO("book of given id: ", userDetail);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer id, @RequestBody@Valid UserDto userDto) {
//        UserDetail newContact = (UserDetail) userService.updateUserData(id, userDto);
        ResponseDTO response = new ResponseDTO("User updated : ",  userService.updateUserData(id, userDto));
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("User" + id +" deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllData() {
        List<UserDetail> userDetail = userService.getListOfUser();
        ResponseDTO response = new ResponseDTO("list of user :", userDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
