package core;


import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;


import dataImport.ParserFactory;
import dataImport.IGraphmlLoader;
import dataImport.IParser;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import model.constructs.DiachronicGraphFactory;
import model.constructs.IDiachronicGraph;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;
import model.metricsReport.IMetricsReport;
import model.metricsReport.ReportFactory;
import parmenidianEnumerations.Metric_Enums;

/**
 * Operating as a manager that communicates with {@link model}
 * @author MK
 * @version {2.0 - modified by KD-MZ-IK}
 * @modified by KD on 2018-10-05
 */

public class ModelManager {
	
	private static ModelManager instance = null;
	private IDiachronicGraph diachronicGraph=null; 
	private DiachronicGraphFactory factory = new DiachronicGraphFactory();
	private GraphMetricsFactory graphMetricsFactory = new GraphMetricsFactory();// added by KD on 2018-10-27
	private IGraphMetrics diachronicGraphMetrics;// added by KD on 2018-10-27
	
	private ParserFactory parserFactory;
	private IParser parser;

	
	protected ModelManager(){
		
		parserFactory = new ParserFactory();
		parser = parserFactory.createHecateParser();
		
	}
	
	
	public static ModelManager getInstance() {
		
		if (instance == null)
			instance = new ModelManager();
		
		return instance;
	}
	
	public void clear(){
		
		if(diachronicGraph!=null)		
			diachronicGraph.clear();
		
	}
	
	public String getTargetFolder(){
		
		return diachronicGraph.getTargetFolder();
		
	}
	
	public void stopConvergence(){
		
		diachronicGraph.stopConvergence();
		
	}
	
	public void saveVertexCoordinates(String projectIni) throws IOException{
		
		diachronicGraph.saveVertexCoordinates(projectIni);
		
	}
	
	public void setTransformingMode(){
		
		diachronicGraph.setTransformingMode();
		
	}
	
	public void setPickingMode(){
		
		diachronicGraph.setPickingMode();
		
	}
	
	
	public void visualize(VisualizationViewer< String, String> visualizationViewer,String projectIni,String targetFolder,int edgeType) throws IOException {
			
		diachronicGraph.saveVertexCoordinates(projectIni);
		//diachronicGraph.visualizeIndividualDBVersions(visualizationViewer,targetFolder,edgeType);
		setVersionsGraph();
		diachronicGraph.visualizeIndividualDBVersions(visualizationViewer,targetFolder,edgeType);
		diachronicGraph.visualizeDiachronicGraph(visualizationViewer);

	}
	
	
	private void setVersionsGraph() {
		
		int i;
		
		for (i=0;i<this.diachronicGraph.getVersions().size();i++) {
			
			GraphMetricsFactory gmFactory = new GraphMetricsFactory();
			IGraphMetrics gm = gmFactory.getDBVersionMetrics(this.diachronicGraph.getVersions().get(i).getVersionTables(), 
					this.diachronicGraph.getVersions().get(i).getVersionForeignKeys());
			this.diachronicGraph.getVersions().get(i).setVersionGraph(gm.getGraph());
			
			
		}
			
		
	}
	
	public Component loadProject(String sql,String xml,String graphml, double frameX,double frameY,
			double scaleX,double scaleY,double centerX,double centerY,String targetFolder,int edgeType) throws Exception{
		
		IGraphmlLoader gl; 
		diachronicGraph=factory.createDiachronicGraph();
		
		
		this.setVersions(sql);
		this.setTransitions(xml);
		diachronicGraph.updateLifetimeWithTransitions();
		parser = parserFactory.createHecateParser();//added on 2018-10-05 by KD
		//added on 2018-10-27 by KD
		//this.diachronicGraphMetrics = this.graphMetricsFactory.getDiachronicGraphMetrics(.getNodes(),this.diachronicGraph.getEdges());
		//this.diachronicGraph.setGraph(this.diachronicGraphMetrics.getGraph());
		
		if (graphml != null) {
			
			parser.createGraphmlLoader(graphml);
			gl = parser.getGraphmlLoader();
			this.diachronicGraphMetrics = this.graphMetricsFactory.getDiachronicGraphMetrics(gl.getNodes(),gl.getEdges());
			this.diachronicGraph.setGraph(this.diachronicGraphMetrics.getGraph());
			this.diachronicGraph.loadDiachronicGraph(gl.getNodes(), gl.getEdges());
			this.diachronicGraph.createVisualizer(sql, targetFolder, edgeType, 1, frameX, frameY, scaleX, scaleY, centerX, centerY);
			//diachronicGraph.loadDiachronicGraph(gl.getNodes(),gl.getEdges(), sql, targetFolder, edgeType, frameX, frameY, scaleX, scaleY, centerX, centerY);
		}else {
			//diachronicGraph.createDiachronicGraph(sql, targetFolder, edgeType, frameX, frameY, scaleX, scaleY,centerX, centerY);
			diachronicGraph.createDiachronicGraph();
			this.diachronicGraphMetrics = this.graphMetricsFactory.getDiachronicGraphMetrics(this.diachronicGraph.getNodes(),this.diachronicGraph.getEdges());
			this.diachronicGraph.setGraph(this.diachronicGraphMetrics.getGraph());
			this.diachronicGraph.createVisualizer(sql, targetFolder, edgeType, 0, frameX, frameY, scaleX, scaleY, centerX, centerY);
		}
		
		return diachronicGraph.show();
		
	}
	
	

	public Component refresh(double forceMult, int repulsionRange) {
		
		return diachronicGraph.refresh(forceMult,repulsionRange);
		
	}
	
	

//created by KD on 13/04/17
	public void generateMetricsReport(String targetFolder, ArrayList<Metric_Enums> metrics){

		ReportFactory reportFactory = new ReportFactory();
		ArrayList<IMetricsReport> reportEngine = new ArrayList<IMetricsReport>();

		for(int i=0;i<metrics.size();i++){

			reportEngine.add(reportFactory.getMetricsReportEngine(targetFolder,metrics.get(i),diachronicGraph,this.diachronicGraphMetrics));
			//reportEngine.get(i).generateMetricsReport();
			reportEngine.get(i).generateMetricsReport();


		}

		reportEngine.clear();

	}

	
	
	/**
	 * @author KD
	 * @param sqlFiles pathname of sql files.
	 * @since 2018-02-14
	 */

	private void setVersions(String sqlFiles) {


		diachronicGraph.setVersions(parser.getLifetime(sqlFiles));


	}

		
		
	/**
	 * @author KD
	 * @param pathname of file containing transitions.
	 * @since 2018-02-14
	 */
			
	private void setTransitions(String xmlFile) {


		diachronicGraph.setTransitions(parser.getTransitions(xmlFile));


	}

	
}
