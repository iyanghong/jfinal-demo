package org.example.demo.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

@Path("/hello")
public class HelloController extends Controller {
    public void index(){
        renderText("Hello world!");
    }

    public void welcome(){
        renderText("welcome to JFinal!");
    }
}
