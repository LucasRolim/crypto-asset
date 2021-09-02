package com.crypto.cryptoassets.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crypto.cryptoassets.service.WalletService;
 
@Controller
@RequestMapping(value = "/crypto")
public class WalletController {
	
	@Autowired
	private WalletService walletService;
 
    @GetMapping(value = "/getConsolidatedPosition")
    @ResponseBody
    public String getConsolidatedPosition() {

        return walletService.getConsolidatedPosition();
    }
 
}