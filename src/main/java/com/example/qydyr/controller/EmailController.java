package com.example.qydyr.controller;

import com.example.qydyr.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailSender emailSender;

    @PostMapping()
    public void sendEmail(@RequestParam(name = "to") String to,
                          @RequestParam(name = "text") String text){
        emailSender.send(to,text);
    }
}
