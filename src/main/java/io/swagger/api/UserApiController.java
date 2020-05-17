package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Device;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserApiController implements UserApi {

    private static final Logger log = LoggerFactory.getLogger(UserApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;


    public UserApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<User> findUser(String uid) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            User user = userRepository.findByUid(uid);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            if(user != null) {
                userRepository.save(user);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }else
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> updateUserTemperaturePreferences(String uid, int temp){
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            User user = userRepository.findByUid(uid);
            if(user != null) {
                user.setTemp(temp);
                userRepository.save(user);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }else
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> getUserTemperaturePreference(String uid){
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            User user = userRepository.findByUid(uid);
            if(user != null)
                return new ResponseEntity<String>(String.valueOf(user.getTemp()),HttpStatus.OK);
            else
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }
}
