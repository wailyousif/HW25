package com.ironyard.api;

import com.ironyard.dto.TIYResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wailm.yousif on 2/22/17.
 */

public class ApiHelper extends Thread
{
    private static final String url = "http://%s:8080/rest/call/%d";
    private Integer number;

    private static final String[] ips = {
            "192.168.2.90",     //matt
            "192.168.2.42",     //cj
            "192.168.2.53",     //rohan
            "192.168.2.144",    //luiz
            "192.168.2.185"     //osman
    };

    public ApiHelper(Integer number)
    {
        this.number = number;
    }

    public void run()
    {
        TIYResponse tiyResponse = invokeNextSystem(number);
    }

    public TIYResponse invokeNextSystem(Integer number)
    {
        int randomNum = ThreadLocalRandom.current().nextInt(0, ips.length);
        //String nextIP = "192.168.2.185";
        String nextIP = ips[randomNum];

        String nextUrl = String.format(url, nextIP, number);
        System.out.println("nextUrl=" + nextUrl);

        RestTemplate restTemplate = new RestTemplate();
        TIYResponse tiyResponse = null;
        try
        {
            tiyResponse = (TIYResponse)(restTemplate.exchange(nextUrl, HttpMethod.GET, getHeaders(), TIYResponse.class)).getBody();
        }
        catch (Exception ex)
        {
            System.out.println("Failed to call api to url (" + nextUrl + "). Exception:" + ex.getMessage());
        }

        return tiyResponse;
    }

    private HttpEntity getHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity headersEntity = new HttpEntity(headers);
        return headersEntity;
    }
}