package com.crypto.cryptoassets.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.crypto.cryptoassets.model.Assets;

@Repository
public class WalletRepository {

	public Assets getCoinCapAccets(String ativo) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Assets> entity = null;
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("https")
				.host("api.coincap.io")
				.path("v2/assets/"+ativo+"/history")
				.queryParam("interval","d1")
				.queryParam("start", "1617753600000")
				.queryParam("end", "1617753601000")
				.build();
		try {
				entity = template.getForEntity(uri.toString(), Assets.class);
		}catch(Exception e) {
			 e.getMessage().contains("429");
			 this.getCoinCapAccets(ativo);
		}
			return entity.getBody();
		}

}