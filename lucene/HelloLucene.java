package lucene;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class HelloLucene {
	  private static Logger log = Logger.getLogger(HelloLucene.class); 	
	  
	public static void main(String[] args) throws IOException, ParseException {
		log.info("start");
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);

		// 1. create the index
		//Directory index = new RAMDirectory();
		Directory index =  FSDirectory.open(new File("C:/temp/myindex.txt"));

		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,	analyzer);

		IndexWriter w = new IndexWriter(index, config);
		log.info("made index");
		
		addDoc(w, "Lucene op in Action", "193398817");
		addDoc(w, "Lucene for oo Dummies is the best actor", "55320055Z");
		addDoc(w, "Managing Gigabytes ui reactor", "55063554A");
		addDoc(w, "The Art of Computer Science", "9900333X");
		addDoc(w, "The lucene of Computer m-power user base Tech", "9900333X");
		addDoc(w, "The Art of Computer Science", "9900333X");
		addDoc(w, "The lucenes of Computer Science", "9900333X");
		for(int i =1; i <900; i++) {
			addDoc(w, i + "The lucenes of Computer Science as400 license The lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 licenseThe lucenes of Computer Science as400 license", "9900333X");
		}
		log.info("added doc of " + w.numDocs());
		w.close();

		// 2. query
		String querystr = args.length > 0 ? args[0] : "m-power user base";

		// the "title" arg specifies the default field to use
		// when no field is explicitly specified in the query.
		Query q = new QueryParser(Version.LUCENE_40, "title", analyzer)	.parse(querystr);

		// 3. search
		int hitsPerPage = 400;
		IndexReader reader = DirectoryReader.open(index);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		searcher.search(q, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		log.info("print...");
		
		// 4. display results
		System.out.println("Found " + hits.length + " hits.");
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = searcher.doc(docId);
			//System.out.println((i + 1) + ". " + d.get("title") );
		}

		// reader can only be closed when there
		// is no need to access the documents any more.
		reader.close();
		
		log.info("done");
	}

	private static void addDoc(IndexWriter w, String title, String isbn)
			throws IOException {
		Document doc = new Document();
		doc.add(new TextField("title", title, Field.Store.YES));

		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("isbn", isbn, Field.Store.YES));
		w.addDocument(doc);
	}
}
