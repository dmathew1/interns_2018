package rahul_jenn.v3_springData.batch;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import rahul_jenn.CONSTANTS;
import rahul_jenn.entity.User;

import javax.sql.DataSource;

/**
 * Created by denze on 7/13/2018.
 */

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource mySqlDatasource;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Bean
    public Job userJob(){
        return jobBuilderFactory.get("userJob")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step_1")
                .<User,User>chunk(5)
                .reader(reader())
                .writer(itemWriter())
                .build();
    }


    @Bean
    public ItemReader<User> reader(){
        FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
        reader.setResource(new ClassPathResource("sample_users.csv"));
        reader.setLineMapper(new DefaultLineMapper<User>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"firstName","lastName"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<User>(){{
                setTargetType(User.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemWriter<User> itemWriter(){
        JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
        writer.setDataSource(mySqlDatasource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
        writer.setSql("insert into user(firstName,lastName) values (:firstName,:lastName)");
        return writer;
    }


}
