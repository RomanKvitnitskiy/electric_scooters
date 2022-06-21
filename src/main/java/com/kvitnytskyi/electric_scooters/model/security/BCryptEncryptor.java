package com.kvitnytskyi.electric_scooters.model.security;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;


public class BCryptEncryptor implements Securable {

    private static final Logger log = Logger.getLogger(BCryptEncryptor.class);

    @Override
    public String encryptPassword(String password) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            log.info(e);
        }
        return null;
    }
}
