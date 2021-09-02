package com.crypto.cryptoassets.service;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.crypto.cryptoassets.model.Assets;
import com.crypto.cryptoassets.model.CsvWallet;
import com.crypto.cryptoassets.model.Response;
import com.crypto.cryptoassets.repository.WalletRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class WalletService {

	@Autowired
	WalletRepository walletRepository ;

	private List<Assets> assetList = new ArrayList<Assets>();
	private List<CsvWallet> listAccetsWallet = new ArrayList<CsvWallet>();

    @Async
    public  CompletableFuture<Assets> getAssincAsset(String asset){
    	System.out.println("Submitted request " + asset + " AT " + getHourNow());
    	return CompletableFuture.supplyAsync(() -> walletRepository.getCoinCapAccets(asset));
    } 

	public String getConsolidatedPosition() {
	
		System.out.println("Start of execution: "+ getHourNow());
		// get csv data
		listAccetsWallet.addAll(this.getCsvWallet());
		
		// get AssetList
		assetList.addAll(getAssetList(getCsvWallet()));

		
		return new Response(getTotal(), getBestAsset(), getBestPerformance(), getWorstAsset(), getWorstPerformance()).toString();
	}


	private List<Assets> getAssetList(List<CsvWallet> listAccetsWallet) {
		
		List<Assets> assetReturn = new ArrayList<Assets>();
		try {
		while (listAccetsWallet.size() > 0) {

			if (listAccetsWallet.size() >= 3) {
				CompletableFuture<Assets>  futureAssetResponse1 = getAssincAsset(listAccetsWallet.get(0).getSymbol());
				CompletableFuture<Assets>  futureAssetResponse2 = getAssincAsset(listAccetsWallet.get(1).getSymbol());
				CompletableFuture<Assets>  futureAssetResponse3 = getAssincAsset(listAccetsWallet.get(2).getSymbol());
				clean(listAccetsWallet, 3);
			
				futureAssetResponse1.thenAccept(s -> assetReturn.add(s));
				futureAssetResponse2.thenAccept(s -> assetReturn.add(s));
				futureAssetResponse3.thenAccept(s -> assetReturn.add(s));
				
				CompletableFuture.allOf(futureAssetResponse1, futureAssetResponse2, futureAssetResponse3);
			
				
				Thread.sleep(10000);

				
			} else if (listAccetsWallet.size() == 2) {
				CompletableFuture<Assets>  futureAssetResponse4 = getAssincAsset(listAccetsWallet.get(0).getSymbol());
				CompletableFuture<Assets>  futureAssetResponse5 = getAssincAsset(listAccetsWallet.get(1).getSymbol());
				clean(listAccetsWallet, 2);
				
				futureAssetResponse4.thenAccept(s -> assetReturn.add(s));
				futureAssetResponse5.thenAccept(s -> assetReturn.add(s));
				
				CompletableFuture.allOf(futureAssetResponse4, futureAssetResponse5);
				
				Thread.sleep(10000);
		
			} else if (listAccetsWallet.size() == 1) {
				CompletableFuture<Assets>  futureAssetResponse6 = getAssincAsset(listAccetsWallet.get(0).getSymbol());
				listAccetsWallet.remove(0);
	
				futureAssetResponse6.thenAccept(s -> assetReturn.add(s));
				
				CompletableFuture.allOf(futureAssetResponse6);
					
				Thread.sleep(10000);
			}
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return assetReturn;
	}
	
	// #TODO put it in util class
	private List<CsvWallet> getCsvWallet() {
		List<CsvWallet> csvWalletList = new ArrayList<CsvWallet>();
		try {

			Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/wallet.csv"));

			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

			List<String[]> wall = csvReader.readAll();

			for (String[] wallet : wall) {
				CsvWallet csvWallet = new CsvWallet(wallet[0], new BigDecimal(wallet[1]), new BigDecimal(wallet[2]));
				csvWalletList.add(csvWallet);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return csvWalletList;
	}

	private String getBestPerformance() {
		// TODO Auto-generated method stub
		return "not_implemented";
	}

	private String getBestAsset() {
		// TODO Auto-generated method stub
		return "not_implemented";
	}

	private String getTotal() {

		//quantity* actual price

		BigDecimal valorAntes = new BigDecimal("37870.5058");// - ;
		BigDecimal valorDepois = new BigDecimal("56999.9728252053067291");
		
		System.out.println(valorAntes.subtract(valorDepois).setScale(2,RoundingMode.HALF_UP));
		
		BigDecimal quantity = new BigDecimal("0.12345");
		
		
		return "not_implemented";
	}
	
	private String getWorstAsset() {
		// TODO Auto-generated method stub
		return "not_implemented";
	}
	
	private String getWorstPerformance() {
		// TODO Auto-generated method stub
		return "not_implemented";
	}
	
	
	private String getHourNow() {
		SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return formatter.format(date);
	}
	
    private void clean(List<CsvWallet> listAccetsWallet, int qtd) {
    	while(qtd != 0) {
    		listAccetsWallet.remove(0);
    		qtd--;
    	}
    }
    
	public List<Assets> getAssetList() {
		return assetList;
	}
	
	public void setAssetList(List<Assets> assetList) {
		this.assetList = assetList;
	}
	

}