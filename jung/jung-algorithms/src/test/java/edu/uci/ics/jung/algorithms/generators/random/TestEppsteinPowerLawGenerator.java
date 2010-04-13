/*
* Copyright (c) 2003, the JUNG Project and the Regents of the University 
* of California
* All rights reserved.
*
* This software is open-source under the BSD license; see either
* "license.txt" or
* http://jung.sourceforge.net/license.txt for a description.
*/
package edu.uci.ics.jung.algorithms.generators.random;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.algorithms.generators.random.EppsteinPowerLawGenerator;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

/**
 * @author Scott White
 */
public class TestEppsteinPowerLawGenerator extends TestCase {
	
	Factory<Graph<Integer,Number>> graphFactory;
	Factory<Integer> vertexFactory;
	Factory<Number> edgeFactory;

	public static Test suite() {
		return new TestSuite(TestEppsteinPowerLawGenerator.class);
	}

	@Override
  protected void setUp() {
		graphFactory = new Factory<Graph<Integer,Number>>() {
			public Graph<Integer,Number> create() {
				return new SparseMultigraph<Integer,Number>();
			}
		};
		vertexFactory = new Factory<Integer>() {
			int count;
			public Integer create() {
				return count++;
			}
		};
		edgeFactory = 
			new Factory<Number>() {
			int count;
			public Number create() {
				return count++;
			}
		};
	}

    public void testSimpleDirectedCase() {

        for (int r=0; r<10; r++) {
            EppsteinPowerLawGenerator<Integer, Number> generator = 
            	new EppsteinPowerLawGenerator<Integer, Number>(graphFactory, vertexFactory, edgeFactory, 10,40,r);
            generator.setSeed(2);

            Graph<Integer, Number> graph = generator.create();
            Assert.assertEquals(graph.getVertexCount(),10);
            Assert.assertEquals(graph.getEdgeCount(),40);
        }

    }

    // TODO: convert what is needed for this test
//    public void testPowerLawProperties() {
//
//        //long start = System.currentTimeMillis();
//        EppsteinPowerLawGenerator generator = new EppsteinPowerLawGenerator(vertexFactory, edgeFactory,
//        		500,1500,100000);
//        generator.setSeed(5);
//        Graph graph = (Graph) generator.generateGraph();
//        //long stop = System.currentTimeMillis();
//        //System.out.println((stop-start)/1000l);
//
//        DoubleArrayList degreeList = DegreeDistributions.getOutdegreeValues(graph.getVertices());
//        int maxDegree = (int) Descriptive.max(degreeList);
//        Histogram degreeHistogram = GraphStatistics.createHistogram(degreeList,0,maxDegree,1);
//        //for (int index=0;index<maxDegree;index++) {
//        //    System.out.println(degreeHistogram.binIndex(index) + " " + degreeHistogram.binHeight(index));
//        //}
//        //if it's power law, 0 is going to have the highest bin count
//        Assert.assertTrue(degreeHistogram.binHeight(0) + degreeHistogram.binHeight(1) > degreeHistogram.binHeight(2) + degreeHistogram.binHeight(3));
//
//        generator = new EppsteinPowerLawGenerator(500,1500,0);
//        graph = (Graph) generator.generateGraph();
//        degreeList = DegreeDistributions.getOutdegreeValues(graph.getVertices());
//        maxDegree = (int) Descriptive.max(degreeList);
//        degreeHistogram = GraphStatistics.createHistogram(degreeList,0,maxDegree,1);
//        //for (int index=0;index<maxDegree;index++) {
//        //    System.out.println(degreeHistogram.binIndex(index) + " " + degreeHistogram.binHeight(index));
//        //}
//        //if it's not power law, 0 is not going to have the highest bin count rather it will start to go up
//        Assert.assertTrue(degreeHistogram.binHeight(0) < degreeHistogram.binHeight(1));
//
//
//
//    }

}
