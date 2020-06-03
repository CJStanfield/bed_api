package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-25T16:55:34.601Z")
@Entity
@Table(name="raspberrypi")
public class Device implements Serializable {
    @Id
    @JsonProperty("did")
    private String did = null;

    @Column(name="temp")
    @JsonProperty("temp")
    private int temp = 0;

    @Column(name="ip")
    @JsonProperty("ip")
    private String ip = null;

    @Column(name="fan_status")
    @JsonProperty("fanStatus")
    private int fanStatus = 0;

    @Column(name="fridge_status")
    @JsonProperty("fridgeStatus")
    private int fridgeStatus = 0;

    @Column(name="desired_temp")
    @JsonProperty("deisredTemp")
    private int desiredTemp = 0;

    /**
     * Get did
     * @return did
     **/
    @ApiModelProperty(example = "1", value = "")
    public String getDid(){return did;}
    public void setDid(String did){this.did = did;}

    /**
     * Get ip
     * @return ip
     **/
    @ApiModelProperty(example = "192.168.86.52", value = "")
    public String getIp(){return ip;}
    public void setIp(String ip){this.ip = ip;}

    /**
     * Get temp
     * @return temp
     **/
    @ApiModelProperty(example = "74", value = "")
    public int getTemp(){return temp;}
    public void setTemp(int temp){this.temp = temp;}

    /**
     * Get fanStatus
     * @return fanStatus
     **/
    @ApiModelProperty(example = "0", value = "")
    public int getFanStatus(){return fanStatus;}
    public void setFanStatus(int fanStatus){this.fanStatus = fanStatus;}

    /**
     * Get fridgeStatus
     * @return fridgeStatus
     **/
    @ApiModelProperty(example = "0", value = "")
    public int getFridgeStatus(){return fridgeStatus;}
    public void setFridgeStatus(int fridgeStatus){this.fridgeStatus = fridgeStatus;}

    /**
     * Get desiredTemp
     * @return desiredTemp
     **/
    @ApiModelProperty(example = "0", value = "")
    public int getDesiredTemp(){return desiredTemp;}
    public void setDesiredTemp(int desiredTemp){this.desiredTemp = desiredTemp;}

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(this.did, device.did) &&
                Objects.equals(this.temp, device.temp) &&
                Objects.equals(this.ip, device.ip)&&
                Objects.equals(this.fanStatus, device.fanStatus)&&
                Objects.equals(this.fridgeStatus, device.fridgeStatus)&&
                Objects.equals(this.desiredTemp, device.desiredTemp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, temp, ip, fanStatus, desiredTemp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Device {\n");

        sb.append("    did: ").append(toIndentedString(did)).append("\n");
        sb.append("    temp: ").append(toIndentedString(temp)).append("\n");
        sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
        sb.append("    fanStatus: ").append(toIndentedString(fanStatus)).append("\n");
        sb.append("    fridgeStatus: ").append(toIndentedString(fridgeStatus)).append("\n");
        sb.append("    desiredTemp: ").append(toIndentedString(desiredTemp)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

