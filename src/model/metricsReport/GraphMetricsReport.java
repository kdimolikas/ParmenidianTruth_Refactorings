package model.metricsReport;

import model.constructs.IDiachronicGraph;
import model.graphMetrics.IGraphMetrics;
import parmenidianEnumerations.Metric_Enums;


/**
 * Creates a report (in .csv format) containing metrics about Diachronic graph of the dataset.
 * @author KD
 * @version 1.0
 * @since 2017-05-23
 *
 */

public class GraphMetricsReport extends MetricsReportEngine {
	
	public GraphMetricsReport(String targetFolder, Metric_Enums metric,IDiachronicGraph diachronicGraph, IGraphMetrics gm){

		this.metric = metric;
		this.edges = diachronicGraph.getEdges();
		this.vertices = diachronicGraph.getNodes();
		super.setDiachronicGraphMetrics(gm);
		this.versions = diachronicGraph.getVersions();
		this.columns = this.versions.size()+2;
		//this.graphMetricsOfDiachronicGraph = diachronicGraph.getGraphMetrics();
		this.targetFolder = targetFolder;


	}
		
//		Implementing Template method populateArray	
		public void populateArray(){
			
			lines = 2;
			
			report = new String[lines][columns];
			
//			create 1st line
			report[0][0]=" ,";
			report[0][1]="Diachronic Graph,";		
			for(int i=0;i<versions.size();i++){
				
				report[0][i+2]=versions.get(i).getVersionName()+",";
			}
			
//			create 1st column		
			report[1][0]=metric.name()+" ,";
			
//			fill in the rest
			for(int i=1;i<columns;i++){
				
					if(i==1)
						report[1][i] = getDiachronicGraphMetricValue(metric.name());
					else
						report[1][i] = getVersionMetricValue(metric.name(),i);
			}
				
		}
		
		
		public String getDiachronicGraphMetricValue(String m){
			
			
			switch(m){
				
				case "NUMBER_OF_CONNECTED_COMPONENTS":	return graphMetricsOfDiachronicGraph.getNumberOfConnectedComponents();
																			
				case "NUMBER_OF_EDGES":	return graphMetricsOfDiachronicGraph.getEdgeCount();
																
				case "NUMBER_OF_EDGES_IN_GCC":	return graphMetricsOfDiachronicGraph.getEdgeCountForGcc();
											
				case "NUMBER_OF_VERTICES":	return graphMetricsOfDiachronicGraph.getVertexCount();
																	
				case "NUMBER_OF_VERTICES_IN_GCC":	return graphMetricsOfDiachronicGraph.getVertexCountForGcc();
																		
				case "GRAPH_DIAMETER":	return graphMetricsOfDiachronicGraph.getGraphDiameter();
										
				default:	return "";
				
			}
			
			
		}
		
		
		public String getVersionMetricValue(String m,int i){

			super.setVersionGraphMetrics(versions.get(i-2).getVersionTables(),versions.get(i-2).getVersionForeignKeys());

			switch(m){

			case "NUMBER_OF_CONNECTED_COMPONENTS": return super.versionGraphMetrics.getNumberOfConnectedComponents();	
			//return versions.get(i-2).generateConnectedComponentsCountReport();

			case "NUMBER_OF_EDGES": return super.versionGraphMetrics.getEdgeCount();	
			//return versions.get(i-2).getEdgeCount();

			case "NUMBER_OF_EDGES_IN_GCC": return super.versionGraphMetrics.getEdgeCountForGcc();	
			//return versions.get(i-2).getEdgeCountForGCC();

			case "NUMBER_OF_VERTICES": return super.versionGraphMetrics.getVertexCount();	
			//return versions.get(i-2).getVertexCount();

			case "NUMBER_OF_VERTICES_IN_GCC": return super.versionGraphMetrics.getVertexCountForGcc();	
			//return versions.get(i-2).getVertexCountForGcc();

			case "GRAPH_DIAMETER": return super.versionGraphMetrics.getGraphDiameter();	
			//return versions.get(i-2).getGraphDiameter();

			default:	return "";
			}


		}

}