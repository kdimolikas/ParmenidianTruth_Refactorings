package model.metricsReport;

import  parmenidianEnumerations.Metric_Enums;
import java.util.EnumSet;

import model.constructs.IDiachronicGraph;
import model.graphMetrics.IGraphMetrics;

public class ReportFactory {
	
	
	private static EnumSet<Metric_Enums> vertexMetricsSet = EnumSet.of(Metric_Enums.VERTEX_OUT_DEGREE,Metric_Enums.VERTEX_IN_DEGREE,Metric_Enums.EDGE_BETWEENNESS,Metric_Enums.VERTEX_BETWEENNESS,Metric_Enums.VERTEX_DEGREE,Metric_Enums.CLUSTERING_COEFFICIENT);
	private static EnumSet<Metric_Enums> graphMetricsSet = EnumSet.complementOf(vertexMetricsSet);
	
	
	//Determines the implementation of populateArray of MetricsReportEngine object
	public IMetricsReport getMetricsReportEngine(String targetFolder, Metric_Enums metric,IDiachronicGraph diachronicGraph,IGraphMetrics gMetrics){
		
		try {

			if (graphMetricsSet.contains(metric)){

				return new GraphMetricsReport(targetFolder, metric,diachronicGraph,gMetrics);

			}else if (vertexMetricsSet.contains(metric)){

				return new VertexMetricsReport(targetFolder, metric,diachronicGraph,gMetrics);			

			}else{
				System.err.println("No metric provided.");
				return null;

			}


		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	
	}

}