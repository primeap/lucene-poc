package org.ap.lucene.poc.index;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	private String INDEX_DIR;
	private IndexWriter indexWriter;
	Integer totalRecord = 120180;
	private static final Integer BATCH_SIZE = 10000;

	public Indexer(String iNDEX_DIR) {
		super();
		INDEX_DIR = iNDEX_DIR;
	}

	public void createIndex() throws IOException {
		createWriter();

		try {
			System.setProperty("batchSize", Integer.toString(BATCH_SIZE));
			ExecutorService executor = Executors.newFixedThreadPool(7);

			// Get total count

			for (int i = 0; i < totalRecord; i = i + BATCH_SIZE) {
				Runnable worker = new IndexWorkerThread(indexWriter, i,totalRecord);
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
				// System.out.println("Indexer.createIndex()...... waiting to
				// terminate");
			}
			indexWriter.commit();
		} finally {
			indexWriter.close();
			System.out.println("Finished all threads");
		}

	}

	private synchronized void createWriter() throws IOException {
		FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		indexWriter = new IndexWriter(dir, config);

	}

}
