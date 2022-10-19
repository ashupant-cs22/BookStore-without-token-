package com.example.bookstoreapp.Model;


import com.example.bookstoreapp.Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer userId;
    public String fullname;
    public String phone;
    public String userName;
    public String password;
    public String email;

    public  UserDetail(UserDto userDto) {

        this.fullname=userDto.getFullname();
        this.phone=userDto.getPhone();
        this.userName=userDto.getUserName();
        this.password=userDto.getPassword();
        this.email=userDto.getEmail();
    }
}
