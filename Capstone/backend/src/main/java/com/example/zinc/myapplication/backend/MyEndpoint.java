/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.zinc.myapplication.backend;

import com.example.SSUMenu;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.zinc.example.com",
                ownerName = "backend.myapplication.zinc.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that get menus from ssumenu and return it
     */

    @ApiMethod(name = "getMenuJSON")
    public MyBean getMenuJSON(){
        MyBean response = new MyBean();
        SSUMenu ssuMenu = new SSUMenu();
        response.setData(ssuMenu.getMenuJson());
        return response;
    }
}
