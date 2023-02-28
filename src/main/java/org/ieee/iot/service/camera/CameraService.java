package org.ieee.iot.service.camera;

/*************************************************
 * Copyright (c) 2023-2-26 Abdullah Sayed Sallam.
 ************************************************/

public interface CameraService {

    byte[] getLiveStream(String url);
}
