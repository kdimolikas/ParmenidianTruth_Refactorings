package model.graphMetrics;

import java.util.ArrayList;

import model.constructs.ForeignKey;
import model.constructs.Table;

public class DBVersionMetrics extends GraphMetrics {

	private ArrayList<Table> tablesWithin;
	private ArrayList<ForeignKey> foreignKeys;
	
	public DBVersionMetrics(ArrayList<Table> nodes, ArrayList<ForeignKey> edges) {
		
		super(nodes, edges);
		tablesWithin = new ArrayList<Table>(nodes);
		foreignKeys = new ArrayList<ForeignKey>(edges);
		
	}

	@Override
	public String generateVertexDegree(String vertex) {

		vertex=vertex.replace(",","");
		
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(tablesWithin.get(i).getKey())){
				vertex=vertex+",";
				return super.generateVrtxDeg(vertex);
			}
		}
		
		return "*,";
	}

	@Override
	public String generateVertexBetweenness(String vertex) {

		vertex=vertex.replace(",","");
		
		
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(tablesWithin.get(i).getKey())){
				vertex=vertex+",";
				return super.generateVrtxBtwn(vertex);
			}
		}
		
		return "*,";
		
	}

	@Override
	public String generateEdgeBetweenness(String edge) {

		edge=edge.replace(",","");
		
				
		for(int i=0;i<foreignKeys.size();++i){
			if(edge.equals(foreignKeys.get(i).getSourceTable()+"|"+foreignKeys.get(i).getTargetTable())){
				edge=edge+",";
				return super.generateEdgeBtwn(edge);
			}
		}
		
		return "*,";
	}

	@Override
	public String generateVertexInDegree(String vertex) {

		vertex=vertex.replace(",","");
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(tablesWithin.get(i).getKey())){
				vertex=vertex+",";
				return super.generateVrtxInDeg(vertex);
			}
		}
		
		return "*,";
	}

	@Override
	public String generateVertexOutDegree(String vertex) {

		vertex=vertex.replace(",","");
		
		
		
		for(int i=0;i<tablesWithin.size();++i){
			if(vertex.equals(tablesWithin.get(i).getKey())){
				vertex=vertex+",";
				return super.generateVrtxOutDeg(vertex);
			}
		}
		
		return "*,";
		
	}


}