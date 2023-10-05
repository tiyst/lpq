package st.tiy.lpq.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import st.tiy.lpq.utils.environments.Dev;

import java.sql.SQLException;

@Dev
@Configuration
public class DevConfiguration {

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server createWebServer() throws SQLException {
		return Server.createWebServer("-web","-webAllowOthers","-webPort","8082");
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}
}
