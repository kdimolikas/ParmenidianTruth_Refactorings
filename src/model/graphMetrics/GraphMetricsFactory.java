package model.graphMetrics;

import java.util.ArrayList;

import model.constructs.ForeignKey;
import model.constructs.Table;

public class GraphMetricsFactory {
	
	
	public IGraphMetrics getDiachronicGraphMetrics(ArrayList<Table> nodes, ArrayList<ForeignKey> edges){
		
		return new DiachronicGraphMetrics(nodes,edges);
	
				
	}
	
	public IGraphMetrics getDBVersionMetrics(ArrayList<Table> nodes, ArrayList<ForeignKey> edges){
		
		return new DBVersionMetrics(nodes,edges);
	
				
	}
	
	
}