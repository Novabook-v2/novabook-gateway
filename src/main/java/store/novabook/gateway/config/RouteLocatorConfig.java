package store.novabook.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import store.novabook.gateway.filter.JwtAuthorizationHeaderFilter;

@Configuration
public class RouteLocatorConfig {

	private final JwtAuthorizationHeaderFilter jwtAuthorizationHeaderFilter;

	public RouteLocatorConfig(JwtAuthorizationHeaderFilter jwtAuthorizationHeaderFilter) {
		this.jwtAuthorizationHeaderFilter = jwtAuthorizationHeaderFilter;
	}

	@Bean
	public RouteLocator myRoute(RouteLocatorBuilder builder) {
		return builder.routes()
			// Auth 서비스
			.route("auth-service", r -> r.path("/auth/**")
				.uri("lb://AUTH-SERVICE")) // Eureka에서 AUTH-SERVICE를 찾음

			// Store 서비스 (JWT 필터 적용)
			.route("store", r -> r.path("/api/v1/store/**")
				.filters(f -> f.filter(jwtAuthorizationHeaderFilter.apply(new JwtAuthorizationHeaderFilter.Config())))
				.uri("lb://STORE"))

			// Coupon 서비스 (JWT 필터 적용)
			.route("coupon", r -> r.path("/api/v1/coupon/**")
				.filters(f -> f.filter(jwtAuthorizationHeaderFilter.apply(new JwtAuthorizationHeaderFilter.Config())))
				.uri("lb://COUPON"))

			.build();
	}
}