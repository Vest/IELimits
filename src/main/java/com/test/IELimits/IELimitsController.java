package com.test.IELimits;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

@Controller
public class IELimitsController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }


    @GetMapping("/ielimit")
    public String ielimit(@RequestParam(name="count", required=true, defaultValue="10") Integer count, Model model) {
        model.addAttribute("count", count);
        return "ielimit";
    }

    @GetMapping("/styles.css")
    public ResponseEntity<StreamingResponseBody> greeting(HttpServletResponse response, @RequestParam(name="count", required=true, defaultValue="10") Integer count, Model model)  {
        model.addAttribute("count", count);
        response.setHeader("Content-Type", "text/css");

        StreamingResponseBody stream = out -> {
            try (PrintWriter writer= new PrintWriter(out)) {
                for (int i = 1; i <= count.intValue(); i++) {
                    writer.println(".div" + i + " { background-color: red; }");
                }
            }
        };

        return ResponseEntity.ok(stream);
    }
}
