package com.solr.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import com.solr.model.Books;


@Repository
public interface BookRepository extends SolrCrudRepository<Books, String> {
	
	@Query(value = "name:*"/*,filters = {"doctype.books"}*/)
	@Facet(fields = {"genre","name","author"})
	public FacetPage<Books> findBySearchTerm(String[] searchTerm , Pageable page);
	
	public List<Books> findByGenre(String genre);
	
	public List<Books> findAll();
	
	//public void addToIndex(Books book);

	
}
