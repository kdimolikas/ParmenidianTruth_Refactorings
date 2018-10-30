package model.metricsReport;

import java.io.File;
import parmenidianEnumerations.Metric_Enums;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.constructs.DBVersion;
import model.constructs.ForeignKey;
import model.constructs.Table;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;


/**
 * Creates reports (in .csv format) containing graph-related metrics.
 * @author KD
 * @since 2017-05-23
 * @version 1.0
 *
 */

public abstract class MetricsReportEngine implements IMetricsReport{
		
	protected String targetFolder;
	private File reportFile;
	protected ArrayList<DBVersion> versions;
	protected IGraphMetrics graphMetricsOfDiachronicGraph;
	protected IGraphMetrics versionGraphMetrics;
	private GraphMetricsFactory graphFactory;
	protected int lines;
	protected int columns;
	protected ArrayList<Table>  vertices;
	protected ArrayList<ForeignKey> edges;
	protected Metric_Enums metric;
	protected String[][] report; 
	private PrintWriter writer;
	
	
	
	protected void setDiachronicGraphMetrics(IGraphMetrics gm) {
		
		this.graphFactory = new GraphMetricsFactory();
		this.graphMetricsOfDiachronicGraph = this.graphFactory.getDiachronicGraphMetrics(vertices, edges);
		this.graphMetricsOfDiachronicGraph = gm;
		
	}
	
	
	protected void setVersionGraphMetrics(ArrayList<Table> tables,ArrayList<ForeignKey> keys ) {
		
		this.graphFactory = new GraphMetricsFactory();
		this.versionGraphMetrics = this.graphFactory.getDBVersionMetrics(tables, keys);
		
	}
	
	//Template method: determines steps to create metrics reports
	//public void generateMetricsReport(){
	public void generateMetricsReport(){
		
				
		createCsvFile();//create a csv report file
			
		populateArray(); //populate array
		
		printArrayIntoFile(); //print array into report file
		
		
	}
	
	
	//defined here
	private void createCsvFile(){
		
		try{
			
			reportFile = new File(targetFolder+"\\Report of "+metric.name().replace("_", " ").toLowerCase()+".csv");
		
		}catch (Exception e){
			
			System.out.println(e.getClass());
			System.out.println("Problem opening the report file.");
		
		}
			
	}
	
	
	//defined in subclasses of this class 
	protected abstract void populateArray();
			
	//defined here
	private void printArrayIntoFile(){
		
		try{
			
			writer = new PrintWriter(reportFile);
			
		} catch (Exception e) {
			
			 System.out.println(e.getClass());
			 System.out.println("Problem writing into the report file.");
		}
		
		for(int i=0;i<lines;i++){
			for(int j=0;j<columns;j++)
				writer.print(report[i][j]);
			writer.print("\n");
		}
				
		writer.close();
	}
	
	public String[][] getReport(){return report;}

}