import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import model.constructs.DiachronicGraph;
import model.graphMetrics.GraphMetricsFactory;
import model.graphMetrics.IGraphMetrics;
import model.metricsReport.MetricsReportEngine;
import model.metricsReport.ReportFactory;
import parmenidianEnumerations.Metric_Enums;

/**
 * Testing {@link model.metricsReport.ReportFactory} class.
 * @author MZ-IK
 * @since 2017-05-23 (Upd. by KD on 2018-10-29)
 * @version 2.0
 *
 */

public class ReportFactoryTest {
	
	private static DiachronicGraph diag;
	private static GraphMetricsFactory gFactory;
	private static IGraphMetrics gMetrics;
	private static ReportFactory rf;
	private static Metric_Enums metric;
	private static Metric_Enums metric1;
	private static final String OUTPUT_FOLDER = "test/test_output";

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		diag = Mockito.mock(DiachronicGraph.class);
		gFactory = new GraphMetricsFactory();
		gMetrics = gFactory.getDiachronicGraphMetrics(diag.getNodes(), diag.getEdges());
		metric=Metric_Enums.CLUSTERING_COEFFICIENT;
		metric1=null;
		rf= new ReportFactory();
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
	public void testGetMetricsReportEngine() {
			MetricsReportEngine mReport=(MetricsReportEngine) rf.getMetricsReportEngine(OUTPUT_FOLDER, metric, diag,gMetrics);
			assertNotNull("MetricsReportEngine object not null", mReport);
			MetricsReportEngine mReport1=(MetricsReportEngine) rf.getMetricsReportEngine(OUTPUT_FOLDER, metric1, diag,gMetrics);
			assertNull("MetricsReportEngine is null", mReport1);
	}

}
