package com.example.Best_Prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Best_Prac.Entity.BiddingModel;
import com.example.Best_Prac.Service.BiddingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bidding")
@Tag(name="",description = "")
public class BiddingController {

    @Autowired
    private BiddingService biddingService;
    @Operation(summary = "Description")
    @PostMapping("/add")
    public ResponseEntity<Object> postBidding(@RequestBody BiddingModel biddingModel) {
        return biddingService.postBidding(biddingModel);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getBidding(@RequestParam double bidAmount) {
        return biddingService.getBidding(bidAmount);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateBidding(@PathVariable int id, @RequestBody BiddingModel biddingModel) {
        return biddingService.updateBidding(id, biddingModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBidding(@PathVariable int id) {
        return biddingService.deleteBidding(id);
    }
}
