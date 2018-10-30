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
import model.constructs.DiachronicGraph;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;
import model.metricsReport.GraphMetricsReport;
import model.metricsReport.ReportFactory;
import parmenidianEnumerations.Metric_Enums;

/**
 * Testing {@link model.metricsReport.GraphMetricsReport} class using "Egee" as dataset.
 * @author MZ-IK
 * @since 2018-03-04 (Upd. by KD on 2018-10-29)
 * @version 2.0
 *
 */

public class GraphMetricsReportTest {
	
	private static DiachronicGraph diag;
	private static Metric_Enums metric;
	private static GraphMetricsReport graphReport;
	
	private static IGraphmlLoader gmlLoader;
	private static GraphmlLoaderFactory gmlFactory;
	private static IParser parser;
	private static ParserFactory pFactory;
	
	private static IGraphMetrics graphMetrics;//added on 2018-101-29
	private static GraphMetricsFactory gFactory;
	private static ReportFactory repFactory;
	
	private static final String INPUT_FOLDER = "test/test_input";
	private static final String OUTPUT_FOLDER = "test/test_output";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		diag = new DiachronicGraph();
		gmlFactory = new GraphmlLoaderFactory();
		gmlLoader = gmlFactory.createGraphmlLoader(INPUT_FOLDER.concat("/layout.graphml"));
		diag.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges());
		pFactory = new ParserFactory();
		parser = pFactory.createHecateParser();
		diag.setVersions(parser.getLifetime(INPUT_FOLDER.concat("/processed schemata")));
		metric=Metric_Enums.GRAPH_DIAMETER;
		
		gFactory = new GraphMetricsFactory();
		graphMetrics = gFactory.getDiachronicGraphMetrics(diag.getNodes(), diag.getEdges());
		
		repFactory = new ReportFactory();
		graphReport = (GraphMetricsReport) repFactory.getMetricsReportEngine(OUTPUT_FOLDER, metric, diag, graphMetrics);
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
	public void testArrayPopulationForGraphMetrics() {
		//tested on MetricsReportEngine
	}

	@Test
	public void testGetDiachronicGraphMetricValue() {
		assertNotNull("DiachronicGraphMetricValue not null", graphReport.getDiachronicGraphMetricValue(metric.name()));
	}

	@Test
	public void testGetVersionMetricValue() {
		assertNotNull("VersionMetricValue not null", graphReport.getVersionMetricValue(metric.name(),2));	
	}

}
