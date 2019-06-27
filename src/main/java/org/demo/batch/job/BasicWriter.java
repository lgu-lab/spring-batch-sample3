package org.demo.batch.job;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class BasicWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("write items");
		for ( String s : items ) {
			System.out.println(" . item : " + s);
		}
	}
	

}
