package com.wedeliver.serviceaccount.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class HashUserPassword {
    private int cpuCost = (int) Math.pow(2, 14); // factor to increase CPU costs
    private int memoryCost = 8;      // increases memory usage
    private int parallelization = 1; // currently not supported by Spring Security
    private int keyLength = 32;      // key length in bytes
    private int saltLength = 64;     // salt length in bytes   
    private SCryptPasswordEncoder sCryptPasswordEncoder;

    public HashUserPassword(){
        this.sCryptPasswordEncoder = new SCryptPasswordEncoder(
            this.cpuCost, 
            this.memoryCost, 
            this.parallelization, 
            this.keyLength, 
            this.saltLength
        );
    }

    public String encode(String rawPassword){
        return this.sCryptPasswordEncoder.encode(rawPassword);
    }

    public boolean decode(CharSequence rawPassword, String encodedPassword){
        return this.sCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
