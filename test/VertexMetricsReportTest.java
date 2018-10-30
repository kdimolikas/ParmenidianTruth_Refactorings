import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dataImport.GraphmlLoaderFactory;
import dataImport.IGraphmlLoader;
import dataImport.IParser;
import dataImport.ParserFactory;
import model.constructs.DiachronicGraphFactory;
import model.constructs.IDiachronicGraph;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;
import model.metricsReport.ReportFactory;
import model.metricsReport.VertexMetricsReport;
import parmenidianEnumerations.Metric_Enums;


/**
 * 
 * Testing {@link model.metricsReport.VertexMetricsReport} class using "Egee" as dataset.
 * @author MZ-IK
 * @since 2017-05-23 (Upd. by KD on 2018-10-29)
 * @version 2.0
 *
 */

public class VertexMetricsReportTest {
	
	private static IDiachronicGraph diag;
	private static DiachronicGraphFactory diagFactory;
	private static Metric_Enums metric;
	private static VertexMetricsReport vertexReport;
	private static IGraphmlLoader gmlLoader;
	private static GraphmlLoaderFactory gmlFactory;
	private static IGraphMetrics DGMetrics;
	private static GraphMetricsFactory gFactory;
	private static ReportFactory repFactory;
	
	private static IParser parser;
	private static ParserFactory pFactory;
	
	private static final String INPUT_FOLDER = "test/test_input";
	private static final String OUTPUT_FOLDER = "test/test_output";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		diagFactory = new DiachronicGraphFactory();
		diag = diagFactory.createDiachronicGraph();
		gmlFactory = new GraphmlLoaderFactory();
		gmlLoader = gmlFactory.createGraphmlLoader(INPUT_FOLDER.concat("/layout.graphml"));
		diag.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges());
		
		pFactory = new ParserFactory();
		parser = pFactory.createHecateParser();
		diag.setVersions(parser.getLifetime(INPUT_FOLDER.concat("/processed schemata")));
		
		gFactory = new GraphMetricsFactory();
		DGMetrics = gFactory.getDiachronicGraphMetrics(diag.getNodes(), diag.getEdges());
		metric=Metric_Enums.VERTEX_OUT_DEGREE;
		repFactory = new ReportFactory();
		vertexReport = (VertexMetricsReport) repFactory.getMetricsReportEngine(OUTPUT_FOLDER, metric, diag, DGMetrics); 
		
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
		String tablename="t_job";
		assertNotNull("DiachronicGraphMetricValue not null", vertexReport.getDiachronicGraphMetricValue(metric.name(),tablename));
	}

	@Test
	public void testGetVersionMetricValue() {

		String tablename="t_job";
		assertNotNull("VersionMetricValue not null", vertexReport.getVersionMetricValue(metric.name(),2,tablename));
		
	}

}
