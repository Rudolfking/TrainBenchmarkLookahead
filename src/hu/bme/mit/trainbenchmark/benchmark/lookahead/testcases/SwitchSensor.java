package hu.bme.mit.trainbenchmark.benchmark.lookahead.testcases;

import hu.bme.mit.inf.lookaheadmatcher.LookaheadMatcherInterface;
import hu.bme.mit.inf.lookaheadmatcher.impl.LookaheadMatching;
import hu.bme.mit.trainbenchmark.benchmark.incquery.util.SwitchSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.exception.IncQueryException;

import Concept.IndividualContainer;
import Concept.Switch;

import com.google.common.collect.Multiset;

public class SwitchSensor extends LookaheadTestCase
{
	protected List<Switch> invalids;
	protected LookaheadMatcherInterface lookahead;

	@Override
	public String getName() {
		return "SwitchSensor";
	}

	@Override
	public void load() {
		bmr.startStopper();
		
		URI resourceURI = FileBroker.getEMFUri(bc.getInstanceModelPath() + "/" + fileName);
		resourceSet = new ResourceSetImpl();
		
		try 
		{
			engine = AdvancedIncQueryEngine.createUnmanagedEngine(resourceSet);
			lookahead = new LookaheadMatcherInterface(engine.getBaseIndex());
			lookahead.InitializeAll(SwitchSensorQuerySpecification.instance(), engine);
		} 
		catch (IncQueryException e)
		{
			e.printStackTrace();
		}
		resource = resourceSet.getResource(resourceURI, true);
		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}
		
		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();
		
		Multiset<LookaheadMatching> res;
		invalids = new ArrayList<Switch>();
		
		try 
		{
			res = lookahead.matchAll(engine, null, SwitchSensorQuerySpecification.instance(), null, null);
			for(LookaheadMatching itRes : res.elementSet())
				invalids.add((Switch)itRes.get(0));
		} 
		catch (IncQueryException e) 
		{
			e.printStackTrace();
		}
		bmr.addInvalid(invalids.size());
		bmr.addCheckTime();
	}

}
