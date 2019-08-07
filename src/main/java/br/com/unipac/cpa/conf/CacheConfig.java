package br.com.unipac.cpa.conf;

import java.util.concurrent.TimeUnit;

import br.com.unipac.cpa.constants.Constants;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CaffeineCache companyTypesCache() {
		return new CaffeineCache(Constants.COMPANYS_TYPES_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache companysInCache() {
		return new CaffeineCache(Constants.COMPANYS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache segmentsCache() {
		return new CaffeineCache(Constants.SEGMENTS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache clientsCache() {
		return new CaffeineCache(Constants.CLIENTS_IN_CACHE,
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

}
