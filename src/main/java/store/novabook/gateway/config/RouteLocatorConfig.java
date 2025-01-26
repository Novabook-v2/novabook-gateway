package store.novabook.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import store.novabook.gateway.filter.JwtAuthorizationHeaderFilter;

@RequiredArgsConstructor
@Configuration
public class RouteLocatorConfig {

	@Bean
	public RouteLocator myRoute(RouteLocatorBuilder builder) {
		return builder.routes().route("auth-service", p -> p.path("/auth/**").uri("lb://AUTH-SERVICE"))
			.route("store", p -> p.path("/api/v1/store/**").and().weight("store", 1).uri("lb://STORE"))
			.route("coupon", p -> p.path("/api/v1/coupon/**").uri("lb://COUPON"))
			.build();
	}
}
