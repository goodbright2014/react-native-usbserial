package com.bmateam.reactnativeusbserial;

import com.facebook.react.bridge.Promise;
import com.hoho.android.usbserial.driver.UsbSerialPort;

import java.io.IOException;

public class UsbSerialDevice {
    private UsbSerialPort port;
    private static final int SERIAL_TIMEOUT = 1000;

    public UsbSerialDevice(UsbSerialPort port) {
        this.port = port;
    }

    public void writeAsync(String value, Promise promise) {

        if (port != null) {

            try {
                port.write(value.getBytes(), SERIAL_TIMEOUT);

                promise.resolve(null);
            } catch (IOException e) {
                promise.reject(e);
            }

        } else {
            promise.reject(getNoPortErrorMessage());
        }
    }

    public byte[] readAsync(Promise promise) {
        final byte[] data = new byte[256];
        try {
             if (port != null) {
            // TODO
                int num = port.read(data , SERIAL_TIMEOUT);
                if(num >0) {
                    return data;
                }

            } else {
                //promise.reject(getNoPortErrorMessage());
            }
        
        } catch(IOException e ) {
           //
        }
        return ([]);
    }

    private Exception getNoPortErrorMessage() {
        return new Exception("No port present for the UsbSerialDevice instance");
    }
}
