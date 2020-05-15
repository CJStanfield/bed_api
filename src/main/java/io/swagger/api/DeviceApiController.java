package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Device;
import io.swagger.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class DeviceApiController implements DeviceApi {
    private static final Logger log = LoggerFactory.getLogger(DeviceApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private DeviceRepository deviceRepository;


    public DeviceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<Device> findDevice(String did) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            Device device = deviceRepository.findDeviceByDid(did);
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        }else
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> updateDeviceInfo(@Valid @RequestBody Device device) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")) {
            Device oldDevice = deviceRepository.findDeviceByDid(device.getDid());
            if(oldDevice == null){
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }else {
                deviceRepository.save(device);
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }else
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> activateFan(String did) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")){
            Device foundDevice = deviceRepository.findDeviceByDid(did);
                if(foundDevice != null) {
                int fanStatus = foundDevice.getFanStatus();
                String apiUrl = "";
                switch (fanStatus){
                    case 0:
                        apiUrl = ":5000/relay/fan?state=0";
                        break;
                    case 1:
                        apiUrl = ":5000/relay/fan?state=1";
                        break;
                    case 2:
                        //this will need to be changed to reflect the correct error code. Device not functioning properly
                        return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
                    default:
                        //Change this as well
                        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                }
                try {
                    String raspberryPiIp = foundDevice.getIp();
                    URL url = new URL("http://" + raspberryPiIp + apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    if (connection.getResponseCode() != 200) {
                        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                    }
                    InputStreamReader in = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //Save the fan information back into the database
                Device updatedDevice = new Device();
                updatedDevice.setDid(foundDevice.getDid());
                updatedDevice.setFanStatus(fanStatus == 0 ? 1 : 0);
                updatedDevice.setIp(foundDevice.getIp());
                updatedDevice.setTemp(foundDevice.getTemp());
                updatedDevice.setFridgeStatus(foundDevice.getFridgeStatus());
                return updateDeviceInfo(updatedDevice);
            }else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> activateFridge(String did) {
        String accept = request.getHeader("Accept");
        if(accept != null && accept.contains("application/json")){
            Device foundDevice = deviceRepository.findDeviceByDid(did);
            if(foundDevice != null) {
                int fridgeStatus = foundDevice.getFridgeStatus();
                String apiUrl = "";
                switch (fridgeStatus){
                    case 0:
                        apiUrl = ":5000/relay/fridge?state=0";
                        break;
                    case 1:
                        apiUrl = ":5000/relay/fridge?state=1";
                        break;
                    case 2:
                        //this will need to be changed to reflect the correct error code. Device not functioning properly
                        return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
                    default:
                        //Change this as well
                        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                }
                try {
                    String raspberryPiIp = foundDevice.getIp();
                    URL url = new URL("http://" + raspberryPiIp + apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    if (connection.getResponseCode() != 200) {
                        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                    }
                    InputStreamReader in = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }
                    connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                }

                //Save the fan information back into the database
                Device updatedDevice = new Device();
                updatedDevice.setDid(foundDevice.getDid());
                updatedDevice.setFanStatus(foundDevice.getFanStatus());
                updatedDevice.setIp(foundDevice.getIp());
                updatedDevice.setTemp(foundDevice.getTemp());
                updatedDevice.setFridgeStatus(fridgeStatus == 0 ? 1 : 0);
                return updateDeviceInfo(updatedDevice);
            }else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        }
        return null;
    }
}
