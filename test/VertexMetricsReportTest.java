import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dataImport.GraphmlLoaderFactory;
import dataImport.IGraphmlLoader;
import model.VertexMetricsReport;
import model.DiachronicGraph;
import parmenidianEnumerations.Metric_Enums;


/**
 * 
 * Testing {@link model.VertexMetricsReport} class using Atlas as dataset.
 * @author MZ-IK
 * @since 2017-05-23
 * @version {2.0 - modified by KD}
 *
 */

public class VertexMetricsReportTest {
	
	private static DiachronicGraph diag;
	private static Metric_Enums metric;
	private static VertexMetricsReport vertexArray;
	private static IGraphmlLoader gmlLoader;
	private static GraphmlLoaderFactory gmlFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	
		diag = new DiachronicGraph();
		gmlFactory = new GraphmlLoaderFactory();
		gmlLoader = gmlFactory.createGraphmlLoader("C:\\Atlas_test\\output\\layout.graphml");
		diag.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges(), "C:\\Users\\PANOS\\Documents\\EvolutionDatasets\\CERN\\Atlas\\processed schemata", "C:\\Atlas_test\\output", 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		metric=Metric_Enums.CLUSTERING_COEFFICIENT;
		vertexArray = new VertexMetricsReport("C:\\Atlas_test\\output\\tests",metric,diag);
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPopulateArray(){
	}

	@Ignore
	public void testArrayPopulationForVertexMetrics() {
		//tested on MetricsReportEngine
	}

	@Test
	public void testGetDiachronicGraphMetricValue() {
		String tablename="";
		assertNotNull("DiachronicGraphMetricValue not null", vertexArray.getDiachronicGraphMetricValue(metric.name(),tablename));
	}

	@Test
	public void testGetVersionMetricValue() {
		String tablename="";
		assertNotNull("VersionMetricValue not null", vertexArray.getVersionMetricValue(metric.name(),1,tablename));
	}

}
