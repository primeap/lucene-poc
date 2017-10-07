package org.ap.lucene.poc.test;

import java.io.IOException;

import org.ap.lucene.poc.index.Indexer;

public class TestIndexProcess {
	public static void main(String[] args) throws IOException {
		Indexer indexer = new Indexer("/home/arvind/index_test/" + System.currentTimeMillis());
		indexer.createIndex();
	}
}
