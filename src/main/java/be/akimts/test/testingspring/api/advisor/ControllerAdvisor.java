package be.akimts.test.testingspring.api.advisor;

import be.akimts.test.testingspring.api.model.dto.ErrorDTO;
import be.akimts.test.testingspring.bll.exceptions.ResourceNotFoundException;
import be.akimts.test.testingspring.bll.exceptions.TitleAlreadyTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(TitleAlreadyTakenException.class)
    ResponseEntity<ErrorDTO> handleTitleTaken(TitleAlreadyTakenException ex){
        return ResponseEntity.badRequest()
                .body(
                        new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST)
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorDTO> handleResourceNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST)
                );
    }

}
