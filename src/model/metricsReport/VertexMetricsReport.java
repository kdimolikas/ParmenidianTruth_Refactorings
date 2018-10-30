package model.metricsReport;


import java.util.Map;

import model.constructs.IDiachronicGraph;
import model.graphMetrics.IGraphMetrics;
import parmenidianEnumerations.Metric_Enums;


/**
 * Creates reports (in .csv format) containing metrics for the vertices of the Diachronic graph.
 * @author KD
 * @since 2017-05-23
 * @version 1.0
 */

public class VertexMetricsReport extends MetricsReportEngine {
	
	
	//GraphMetricsFactory gmFactory;
	
	
	public VertexMetricsReport(String targetFolder, Metric_Enums metric,IDiachronicGraph diachronicGraph, IGraphMetrics gm){
		
		//gmFactory = new GraphMetricsFactory();
		this.metric = metric;
		this.edges = diachronicGraph.getEdges();
		this.vertices = diachronicGraph.getNodes();
		super.setDiachronicGraphMetrics(gm);
		this.versions = diachronicGraph.getVersions();
		this.columns = this.versions.size()+2;
		//this.graphMetricsOfDiachronicGraph = diachronicGraph.getGraphMetrics();//2018-10-28
		this.targetFolder = targetFolder;
		
				
	}
		
	//Implementing abstract method "populateArray"
	public void populateArray(){

		Map<String,Double> collection=null;

		if (metric.name() == "EDGE_BETWEENNESS"){
			lines = edges.size()+1;
		}else{
			lines = vertices.size()+1;
		}

		report = new String[lines][columns];

		//create 1st line
		report[0][0]=" ,";
		report[0][1]="Diachronic Graph,";		
		for(int i=0;i<versions.size();i++){			

			report[0][i+2]=versions.get(i).getVersionName()+",";
		}

		//create 1st column			
		if (metric.name() == "EDGE_BETWEENNESS"){


			for(int i=0;i<edges.size();i++)
				report[i+1][0]=edges.get(i).getSourceTable()+"|"+edges.get(i).getTargetTable()+",";		

		}else{

			for(int i=0;i<vertices.size();i++)
				report[i+1][0]=vertices.get(i).getKey()+",";	
		}

		//fill in the rest

		if (metric.name() != "CLUSTERING_COEFFICIENT"){
			
			for(int i=1;i<columns;i++){
				for(int j=1;j<lines;j++)
					if(i==1)
						report[j][i] = getDiachronicGraphMetricValue(metric.name(),report[j][0]);
					else
						report[j][i]=getVersionMetricValue(metric.name(),i, report[j][0]);
			}

		}else{

			for(int i=1;i<columns;i++){
				for(int j=1;j<lines;j++)
					if(i==1){

						if(collection==null)
							collection = graphMetricsOfDiachronicGraph.getClusteringCoefficient();

						String candidate =report[j][0].replace(",", "");
						String clusteringCoefficientScore=String.valueOf(collection.get(candidate));
						if(clusteringCoefficientScore.equals("null"))
							clusteringCoefficientScore="*";
						report[j][i] =  clusteringCoefficientScore +",";

					}else{

						if(collection==null) {
							super.setVersionGraphMetrics(versions.get(i-2).getVersionTables(), versions.get(i-2).getVersionForeignKeys());
							collection = super.versionGraphMetrics.getClusteringCoefficient();
						}
							//this.graphMetricsOfDiachronicGraph.getClusteringCoefficient(); 
							//versions.get(i-2).getClusteringCoefficient();

						String candidate =report[j][0].replace(",", "");
						String clusteringCoefficientScore=String.valueOf(collection.get(candidate));
						if(clusteringCoefficientScore.equals("null"))
							clusteringCoefficientScore="*";
						report[j][i] =  clusteringCoefficientScore +",";
					}
				collection.clear();
				collection=null;
			}

		}
	}
		
		
	public String getDiachronicGraphMetricValue(String m, String tableName){


		switch(m){

		case "VERTEX_OUT_DEGREE":	return graphMetricsOfDiachronicGraph.generateVertexOutDegree(tableName);

		case "VERTEX_IN_DEGREE":	return graphMetricsOfDiachronicGraph.generateVertexInDegree(tableName);

		case "EDGE_BETWEENNESS":	return graphMetricsOfDiachronicGraph.generateEdgeBetweenness(tableName);

		case "VERTEX_BETWEENNESS":	return graphMetricsOfDiachronicGraph.generateVertexBetweenness(tableName);

		case "VERTEX_DEGREE":	return graphMetricsOfDiachronicGraph.generateVertexDegree(tableName);

		default:	return "";

		}


	}
		
		
	public String getVersionMetricValue(String m,int i,String tableName){

		//IGraphMetrics gm = gmFactory.getDBVersionMetrics(versions.get(i-2).getVersionTables(), versions.get(i-2).getVersionForeignKeys());
		super.setVersionGraphMetrics(versions.get(i-2).getVersionTables(),versions.get(i-2).getVersionForeignKeys());

		switch(m){

		case "VERTEX_OUT_DEGREE":	return super.versionGraphMetrics.generateVertexOutDegree(tableName);
			//return versions.get(i-2).generateVertexOutDegree(tableName);

		case "VERTEX_IN_DEGREE": return super.versionGraphMetrics.generateVertexInDegree(tableName);	
			//return versions.get(i-2).generateVertexInDegree(tableName);

		case "EDGE_BETWEENNESS": return super.versionGraphMetrics.generateEdgeBetweenness(tableName);	
			//return versions.get(i-2).generateEdgeBetweenness(tableName);

		case "VERTEX_BETWEENNESS": return super.versionGraphMetrics.generateVertexBetweenness(tableName);	
			//return versions.get(i-2).generateVertexBetweenness(tableName);			

		case "VERTEX_DEGREE": return super.versionGraphMetrics.generateVertexDegree(tableName);	
			//return versions.get(i-2).generateVertexDegree(tableName);

		default:	return "";
		}

	}

}