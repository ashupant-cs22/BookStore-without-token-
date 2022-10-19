package com.example.bookstoreapp.Exception;

import com.example.bookstoreapp.Dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BookException {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseDTO>handleException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String >errorMessage=errorList.stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO=new ResponseDTO("Exception while Processing Rest Request",errorMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
  }
        @ExceptionHandler(BookStoreExceptionMessage.class)
        public ResponseEntity<ResponseDTO>handleMessage(BookStoreExceptionMessage exceptionMessage){
        ResponseDTO responseDTO=new ResponseDTO("exception while processing REST request",exceptionMessage.getMessage());
        return  new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
  }
  }
