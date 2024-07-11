package st.tiy.lpq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebSecurityConfiguration {

	@Bean
	public WebMvcConfigurer corsConfigurer() {

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedMethods(CorsConfiguration.ALL)
						.allowedHeaders(CorsConfiguration.ALL)
						.allowedOriginPatterns(CorsConfiguration.ALL);
			}
		};
	}

}
