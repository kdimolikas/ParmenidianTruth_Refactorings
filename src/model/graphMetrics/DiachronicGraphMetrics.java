package model.graphMetrics;

import java.util.ArrayList;


import model.constructs.ForeignKey;
import model.constructs.Table;

public class DiachronicGraphMetrics extends GraphMetrics {

	public DiachronicGraphMetrics(ArrayList<Table> nodes, ArrayList<ForeignKey> edges) {
		super(nodes, edges);
		
	}

	
	@Override
	public String generateVertexDegree(String vertex) {
		
		return super.generateVrtxDeg(vertex);
	}

	
	@Override
	public String generateVertexBetweenness(String vertex) {

		return super.generateVrtxBtwn(vertex);
		
	}

	@SuppressWarnings({ })
	@Override
	public String generateEdgeBetweenness(String edge) {
		
		return super.generateEdgeBtwn(edge);
	}

	@Override
	public String generateVertexInDegree(String vertex) {

		return super.generateVrtxInDeg(vertex);
	}

	@Override
	public String generateVertexOutDegree(String vertex) {

		return super.generateVrtxOutDeg(vertex);
		
	}

}
