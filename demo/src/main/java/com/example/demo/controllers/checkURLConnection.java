package com.example.demo.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class checkURLConnection {

    public final String SITE_IS_UP = "Site is up!";
    public final String SITE_IS_DOWN = "Site is down!";
    public final String INCORRECT_URL = "Site is incorrect!";

    @GetMapping("/check")
    public String getURLStatusMessage(@RequestParam String url){
        String returnMessage = "";
        try{
        URL urlObject = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int respCode = conn.getResponseCode() / 100;

        if (respCode != 2){
            returnMessage = SITE_IS_DOWN;
            } else {
            returnMessage = SITE_IS_UP;
            }
        } catch(MalformedURLException e){
            returnMessage = INCORRECT_URL;
        } catch(IOException e){
            returnMessage = SITE_IS_DOWN;
        }
        return returnMessage;
    }
    
}
