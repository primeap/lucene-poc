package org.ap.lucene.poc.index;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import org.ap.lucene.poc.model.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

public class IndexWorkerThread implements Runnable {
	private IndexWriter indexWriter;
	private Integer startIndex;
	private Integer totalRecord;

	public IndexWorkerThread(IndexWriter indexWriter, Integer startIndex, Integer totalRecord) {
		this.indexWriter = indexWriter;
		this.startIndex = startIndex;
		this.totalRecord =  totalRecord;
	}

	public void run() {
		int batchSize = Integer.parseInt(System.getProperty("batchSize"));
		Random rm = new Random();
		Integer loopEnd = Math.min(startIndex + batchSize, totalRecord);
		try {
			
			for (int i = startIndex; i < loopEnd; i++) {
				Product p = new Product(i, "name-" + i, new BigDecimal(i), "company-" + i);
				Document document = new Document();
				document.add(new StringField("id", i + "", Field.Store.YES));
				document.add(new TextField("name", p.getName(), Field.Store.YES));
				document.add(new TextField("price", p.getPrice().toString(), Field.Store.YES));
				document.add(new TextField("company", p.getCompany(), Field.Store.YES));
				indexWriter.addDocument(document);
			}
			indexWriter.flush();
			Integer sleepTime = rm.nextInt();
			sleepTime = sleepTime > 0 ? sleepTime : -1 * sleepTime;
			Thread.sleep((1000 + sleepTime % 10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " startIndex:" + startIndex + "  end:"
				+ (loopEnd-1));

	}

}
