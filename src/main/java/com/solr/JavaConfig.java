package com.solr;  


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//@EnableSolrRepositories(basePackages = "com.solr.repositories")
//@ComponentScan
public class JavaConfig {

	
	@Bean
	public ViewResolver getViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/view/");
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(JstlView.class);
	    return resolver;
	}

	/*@Bean
    public SolrClient solrClient() {
        return new HttpSolrClient("http://localhost:8983/solr/books");
    }
 
    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }*/
	
} 
 
