package hu.bme.mit.trainbenchmark.benchmark.lookahead;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;

public class LookaheadBenchmarkConfig extends BenchmarkConfig {

	public LookaheadBenchmarkConfig(String[] args) throws ParseException {
		super(args);
	}
	
	protected boolean deltaMonitor;
	
	@Override
	public void processArguments(String[] args) throws ParseException
	{
		super.processArguments(args);

		if (cmd.hasOption("noDeltaMonitor"))
		{
			deltaMonitor = false;
		} else {
			deltaMonitor = true;
		}
	};
}
