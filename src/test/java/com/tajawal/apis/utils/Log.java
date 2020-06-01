package com.tajawal.apis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Log {

    private Logger Log = LoggerFactory.getLogger(Logger.class);

    public void asInfo(String message) {
        Log.info(message);
    }

    public void asWarning(String message) {
        Log.warn(message);
    }

    public void asError(String message, String exception) {
        Log.error(message, exception);
    }

    public void asDebug(String message) {
        Log.debug(message);
    }

}
