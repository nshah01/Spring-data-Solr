package com.solr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.http.HttpRequest;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetFieldEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.solr.model.Books;
import com.solr.repositories.BookRepository;
import com.solr.service.BookService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookRepository bookRepo;
	
	SolrClient solrClient;
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	/*@RequestMapping(value="/create")
	public void insert() {
		bookService.createSampleData();
	}*/
	
	@RequestMapping(value = "/search" , method = RequestMethod.GET)
	public FacetPage<Books> searchResults(HttpServletRequest request , @ModelAttribute Books books,Model model) {
	String search = request.getParameter("searchTerm");
	String searchTerm[] = search.split(" ");
	System.out.println("=============================" + searchTerm);
	
	/*SolrQuery query = new SolrQuery();
	query.addFacetQuery(searchTerm);
	System.out.println(query);
	try {
		QueryResponse response = solrClient.query(query);
		Map<String,Integer> facetQueryMap = response.getFacetQuery();
		System.out.println(facetQueryMap.toString());
	} catch (SolrServerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}*/
	FacetPage<Books> booksFacetPage = bookRepo.findBySearchTerm(searchTerm, new PageRequest(0, 40));
	booksFacetPage.getContent();
	System.out.println("------------------------" +booksFacetPage.getContent());
	//FacetOptions options = new FacetOptions();
	//options.addFacetOnField("name");
	//options.addFacetOnField("author");
	for(Page<? extends FacetEntry> page : booksFacetPage.getAllFacets()) {
		System.out.println("#############################"+booksFacetPage.getAllFacets());
		for(FacetEntry facetEntry : page.getContent()) {
			String termName = facetEntry.getValue();
			System.out.println("-------------------------"+termName);
			long count = facetEntry.getValueCount();
			System.out.println("-------------------------"+count);
		}
	}
	return booksFacetPage;
	}	
	
	@RequestMapping(value = "/searchGenre" , method = RequestMethod.GET)
	public List<Books> searchByGenre(HttpServletRequest request){
		String genre = request.getParameter("genre");
		List<Books> bookList = new ArrayList<Books>();
		bookList = bookRepo.findByGenre(genre);
		//String list = bookList.toString();
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\" +bookList);
		return bookList;
	}
	
	@RequestMapping(value = "/getAll" , method = RequestMethod.GET)
	public List<Books> getAll() {
		List<Books> bookList = new ArrayList<Books>();
		bookList = bookRepo.findAll();
		//String book = bookList.toString();
		System.out.println("............................."+bookList);
		return bookList;
	}
	
	/*@RequestMapping(value = "/search")
	public void searchResults(HttpServletRequest request) throws SolrServerException, IOException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setFacet(true);
		solrQuery.setFacetMinCount(1);
		solrQuery.setRows(new Integer(100));
		solrQuery.setStart(new Integer(0));
		solrQuery.addFacetField("name");
		solrQuery.addFacetField("genre");
		solrQuery.addFacetField("author");
		
		
		String searchString = request.getParameter("searchTerm");
		System.out.println("^^^^^^^^^^^^^^^^^^^^"+searchString);
		String[] searchTerm = searchString.split("");
		
		solrQuery.setQuery(searchString.toString());
		
		Map searchMap = request.getParameterMap();
		Set keySet = searchMap.keySet();
		Iterator searchMapIterator = keySet.iterator();
		while(searchMapIterator.hasNext()) {
			String key = (String)searchMapIterator.next();
			String [] values = (String [])searchMap.get(key);
			for(int i=0;i<values.length;i++) {
				solrQuery.addFilterQuery(key + ":" + values[i]);
			}
		}
	
	QueryResponse queryResponse = solrClient.query(solrQuery);
	List list = queryResponse.getResults();
	System.out.println("*********************" +list);
	}*/

}
