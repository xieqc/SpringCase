package com.xie.springcase.test;

import java.io.File;
import java.io.IOException;

import junit.framework.JUnit4TestAdapter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class luceneTest {
	Logger logger = LoggerFactory.getLogger(luceneTest.class);
	private Directory dir;
	
	@BeforeClass
	public static void initialize() {
	}
	
	@AfterClass
	public static void end() {
	}
	
	public static junit.framework.Test suite() {
		 return new JUnit4TestAdapter(luceneTest.class);
	}
	
	@Test
	public void createIndexFile() {
		try {
			dir = FSDirectory.open(new File("D://tmp/lucene/index"));
			IndexWriter writer=getWriter();
			for(int i=0; i < 10; i++) {
				Document doc=new Document();
				doc.add(new StringField("id", String.valueOf(i), Store.YES));
				doc.add(new TextField("content", "test iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+i, Store.YES));
				writer.addDocument(doc);
			}
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void indexSearch() {
		try {
			Directory dir=FSDirectory.open(new File("D://tmp/lucene/index"));
			IndexReader reader=DirectoryReader.open(dir);
			IndexSearcher searcher=new IndexSearcher(reader);
			Term term=new Term("content", "test");
			TermQuery query=new TermQuery(term);
			TopDocs topdocs=searcher.search(query, 15);
			ScoreDoc[] scoreDocs=topdocs.scoreDocs;
			System.out.println("查询结果总数---" + topdocs.totalHits+"最大的评分--"+topdocs.getMaxScore());
			for(int i=0; i < scoreDocs.length; i++) {
				int doc = scoreDocs[i].doc;
				Document document = searcher.doc(doc);
				System.out.println("content===="+document.get("content"));
				System.out.println("id--" + scoreDocs[i].doc + "---scors--" + scoreDocs[i].score+"---index--"+scoreDocs[i].shardIndex);
			}
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * 获得IndexWriter对象 
	 * @return 
	 * @throws Exception 
	 */
	public IndexWriter getWriter() throws Exception {
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_4_10_4);
		IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_4_10_4, analyzer);
		return new IndexWriter(dir, iwc);
	} 
}
