package com.sarka.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/getTest")
    public String getTest(){
        return "get request received";
    }
    // Test POST endpoint
    @PostMapping("/postTest")
    public String postTest(@RequestBody String data) {
        return "POST request received with data: " + data;
    }

    // Test PUT endpoint
    @PutMapping("/putTest")
    public String putTest(@RequestBody String data) {
        return "PUT request received with data: " + data;
    }
    // Test DELETE endpoint
  //  @DeleteMapping("/deleteTest")
    @DeleteMapping("/deleteTest")
    public String deleteTest() {
        return "DELETE request received!";
    }

}
