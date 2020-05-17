package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-25T16:55:34.601Z")

@Api(value="user", description="Users api")
public interface UserApi {

    @ApiOperation(value = "Get a user's information", nickname="FindUser", notes = "", tags={"Users"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found user"),
            @ApiResponse(code = 400, message = "Invalid user information")
    })
    @RequestMapping(value = "/users",
    produces = {"application/json"},
    method = RequestMethod.GET)
    ResponseEntity<User> findUser(@ApiParam(value = "", required = true) @PathVariable("uid") String uid);

    @ApiOperation(value = "Create a new user", nickname="CreateUser", notes = "", tags={"Users"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created User"),
            @ApiResponse(code = 400, message = "Invalid user data"),
            @ApiResponse(code = 409, message = "User already exists")
    })
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user);

    @ApiOperation(value = "Update users ideal temperature", nickname="updateTemperature", notes = "", tags={"Users"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated User temperature preferences"),
            @ApiResponse(code = 400, message = "Invalid user or temperature data"),
    })
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateUserTemperaturePreferences(@ApiParam(value = "", required = true) @PathVariable String uid, @PathVariable("temp") int temp);

    @ApiOperation(value = "Get a users ideal temperature", nickname="getTemperature", notes = "", tags={"Users"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully returned a user's temperature"),
            @ApiResponse(code = 400, message = "Invalid user id"),
    })
    @RequestMapping(value = "/users/temperature",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Integer> getUserTemperaturePreference(@ApiParam(value = "", required = true) @PathVariable String uid);
}
