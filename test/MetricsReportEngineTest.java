import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import dataImport.GraphmlLoaderFactory;
import dataImport.IGraphmlLoader;
import model.constructs.DiachronicGraph;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;
import model.metricsReport.GraphMetricsReport;
import model.metricsReport.VertexMetricsReport;
import parmenidianEnumerations.Metric_Enums;

/**
 * Testing {@link model.metricsReport.MetricsReportEngine} class using "Egee" as dataset.
 * @author MZ-IK
 * @since 2018-03-04 (Upd. by KD on 2018-10-29)
 * @version 2.0
 *
 */

public class MetricsReportEngineTest {
	
	private static GraphMetricsReport graphReport;
	private static VertexMetricsReport vertexReport;
	private static DiachronicGraph diag;
	private static Metric_Enums vertexMetric;
	private static Metric_Enums graphMetric;
	private static GraphMetricsReport spyGraph;
	private static VertexMetricsReport spyVertex;
	private static IGraphmlLoader gmlLoader;
	private static GraphmlLoaderFactory gmlFactory;
	private static IGraphMetrics gMetrics;
	private static GraphMetricsFactory gFactory;
	private static final String INPUT_FOLDER = "test/test_input";
	private static final String OUTPUT_FOLDER = "test/test_output";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		
		diag = new DiachronicGraph();
		gmlFactory = new GraphmlLoaderFactory();
		gmlLoader = gmlFactory.createGraphmlLoader(INPUT_FOLDER.concat("/layout.graphml"));
		diag.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges()); 
		gFactory = new GraphMetricsFactory();
		gMetrics = gFactory.getDiachronicGraphMetrics(diag.getNodes(), diag.getEdges());
		vertexMetric=Metric_Enums.CLUSTERING_COEFFICIENT;
		graphMetric=Metric_Enums.NUMBER_OF_VERTICES;
		graphReport = new GraphMetricsReport(OUTPUT_FOLDER,graphMetric, diag, gMetrics);
		vertexReport = new VertexMetricsReport(OUTPUT_FOLDER,vertexMetric, diag, gMetrics);
		spyGraph = Mockito.spy(graphReport);
		spyVertex = Mockito.spy(vertexReport);
		
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
	public void testGenerateMetricsReport() {
		assertNull("graph report array is not filled", spyGraph.getReport());
		assertNull("vertex report array is not filled", spyVertex.getReport());
		spyGraph.generateMetricsReport();
		spyVertex.generateMetricsReport();
		assertNotNull("graph report array filled", spyGraph.getReport());
		assertNotNull("vertex report array filled", spyVertex.getReport());
	}

	@Ignore
	public void testCreateCsvFile() {
		//just opens a file
	}

	@Ignore
	public void testPopulateArray() {
		//abstract
	}

	@Ignore
	public void testPrintArrayIntoFile() {
		//just writes the array content to the file
	}

}
