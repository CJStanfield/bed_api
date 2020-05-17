package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-25T16:55:34.601Z")
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @JsonProperty("uid")
    private String uid = null;

    @Column(name="did")
    @JsonProperty("did")
    private String did = null;

    @Column(name="pwd")
    @JsonProperty("pwd")
    private String pwd = null;

    @Column(name="ideal_temp")
    @JsonProperty("temp")
    private int temp = 0;

    @ApiModelProperty(example = "1", value = "")
    public String getUid(){return uid;}
    public void setUid(String uid) {this.uid = uid;}

    @ApiModelProperty(example = "1", value = "")
    public String getDid(){return did;}
    public void setDid(String did) {this.did = did;}

    @ApiModelProperty(example = "passwordIsATerriblePassword", value = "")
    public String getPwd(){return pwd;}
    public void setPwd(String pwd) {this.pwd = pwd;}

    @ApiModelProperty(example = "76", value = "")
    public int getTemp(){return temp;}
    public void setTemp(int temp) {this.temp = temp;}

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(this.uid, user.uid) &&
                Objects.equals(this.did, user.did) &&
                Objects.equals(this.pwd, user.pwd) &&
                Objects.equals(this.temp, user.temp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, did, pwd, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Device {\n");

        sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
        sb.append("    did: ").append(toIndentedString(did)).append("\n");
        sb.append("    pwd: ").append(toIndentedString(pwd)).append("\n");
        sb.append("    temp: ").append(toIndentedString(temp)).append("\n");
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
