package io.swagger.repository;

import io.swagger.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {
    Device findDeviceByDid(String did);
}
