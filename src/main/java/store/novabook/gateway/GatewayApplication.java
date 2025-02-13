package store.novabook.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RefreshScope
public class GatewayApplication {

	public static void main(String[] args) {
		System.out.println("2월 11일 15:02 반영");
		SpringApplication.run(GatewayApplication.class, args);
	}

}
