package com.solr.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrTemplate;

import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.stereotype.Service;

import com.solr.model.Books;
import com.solr.repositories.BookRepository;

@Service
//@EnableSolrRepositories(basePackages = "com.solr.repositories")
//@PropertySource("classpath : application.properties")
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public FacetPage<Books> facetSearch(String[] searchTerm) {
	FacetPage<Books> booksFacetPage = bookRepository.findBySearchTerm(searchTerm, new PageRequest(0, 40));
	booksFacetPage.getContent();
	
	for(Page<? extends FacetEntry> page : booksFacetPage.getAllFacets()) {
		for(FacetEntry facetEntry : page.getContent()) {
			String termName = facetEntry.getValue();
			long count = facetEntry.getValueCount();
		}
	}
	return booksFacetPage;
	}
	
	
	
}
