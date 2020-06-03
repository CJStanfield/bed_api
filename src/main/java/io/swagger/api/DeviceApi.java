package io.swagger.api;


import io.swagger.annotations.*;
import io.swagger.model.Device;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-25T16:55:34.601Z")

@Api(value="device", description="Device api")
public interface DeviceApi {

    @ApiOperation(value = "Get a device's information", nickname="FindDevice", notes = "", tags={"Devices"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully found device"),
            @ApiResponse(code = 400, message = "Invalid device information")
    })
    @RequestMapping(value = "/device",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Device> findDevice(@ApiParam(value = "", required = true) @PathVariable("did") String did);

    @ApiOperation(value = "Update device information like temperature", nickname="UpdateDevice", notes = "", tags={"Devices"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated device information"),
            @ApiResponse(code = 400, message = "Invalid device information")
    })
    @RequestMapping(value = "/device/deviceInfo",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateDeviceInfo(@ApiParam(value = "", required = true) @Valid @RequestBody Device device);

    @ApiOperation(value = "Turn on/off fan device", nickname="PowerDevice", notes = "", tags={"Devices"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully powered the device"),
            @ApiResponse(code = 400, message = "Invalid device information")
    })
    @RequestMapping(value = "/device/fanPower",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Void> activateFan(@ApiParam(value = "", required = true) @PathVariable("did") String did, @PathVariable("state") int state);

    @ApiOperation(value = "Turn on/off fridge device", nickname="PowerDevice", notes = "", tags={"Devices"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully powered the device"),
            @ApiResponse(code = 400, message = "Invalid device information")
    })
    @RequestMapping(value = "/device/fridgePower",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Void> activateFridge(@ApiParam(value = "", required = true) @PathVariable("did") String did, @PathVariable("state") int state);

    @ApiOperation(value = "Update Device temperature", nickname="UpdateDeviceTemperature", notes = "", tags={"Devices"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated the device temperature"),
            @ApiResponse(code = 400, message = "Invalid device information")
    })
    @RequestMapping(value = "/device/temperature",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Void> updateTemperature(@ApiParam(value = "", required = true) @PathVariable("did") String did, @PathVariable("state") int temperature);

}
