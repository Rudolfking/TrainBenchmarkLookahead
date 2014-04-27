package hu.bme.mit.trainbenchmark.benchmark.lookahead;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class Main {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ParseException, IOException
	{
		LookaheadBenchmarkLogic benchmarkLogic = new LookaheadBenchmarkLogic(args);
		benchmarkLogic.runBenchmarks();
	}

}
