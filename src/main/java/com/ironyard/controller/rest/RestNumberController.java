package com.ironyard.controller.rest;

import com.ironyard.api.ApiHelper;
import com.ironyard.dto.TIYResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wailm.yousif on 2/23/17.
 */

@RestController
public class RestNumberController
{
    private static final Integer WAITING_SECONDS = 2;
    private static final Integer DEFAULT_STARTING_NUMBER = 25;

    @RequestMapping(path = "/rest/call/{number}", method = RequestMethod.GET)
    public TIYResponse sayNumber(@PathVariable Integer number, HttpServletRequest request) throws InterruptedException
    {
        if (number == null)
            number = DEFAULT_STARTING_NUMBER;

        System.out.println("Received call from: " + request.getRemoteAddr() + " with number: " + number);

        //say the number
        TIYResponse tiyResponse = new TIYResponse(false, number);
        ProcessBuilder pb;
        if (number > 0)
            pb = new ProcessBuilder("say", Integer.toString(number));
        else
            pb = new ProcessBuilder("say", "Liftoff");

        try
        {
            Process p = pb.start();
            tiyResponse.setSuccess(true);
        }
        catch (Exception ex)
        {
            System.out.println("Exception:" + ex.getMessage());
        }

        Thread.sleep((long) WAITING_SECONDS *1000);

        //start a thread to do next call
        if (number > 0)
        {
            ApiHelper apiHelper = new ApiHelper(number-1);
            apiHelper.start();
        }

        return tiyResponse;
    }
}
