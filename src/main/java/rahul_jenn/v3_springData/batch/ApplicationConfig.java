package rahul_jenn.v3_springData.batch;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import rahul_jenn.CONSTANTS;

import javax.sql.DataSource;

/**
 * Created by denze on 7/15/2018.
 */

@Configuration
public class ApplicationConfig {

    @Bean(name = "datasource")
    public DataSource mySqlDatasource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(CONSTANTS.DB_URL);
        dataSource.setUser(CONSTANTS.USERNAME);
        dataSource.setPassword(CONSTANTS.PASSWORD);
        return dataSource;
    }

}
