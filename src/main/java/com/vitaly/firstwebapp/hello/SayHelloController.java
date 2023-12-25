package com.vitaly.firstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello world!!! Im here";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        String body = """
                 <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Hello web page</title>
                </head>
                <body>
                    <h1>Hi!</h1>
                </body>
                </html>
                """;
        return body;
    }

    @RequestMapping("say-hello-jsp")
    //src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    public String sayHelloJsp() {
        return "sayHello";
    }


}
