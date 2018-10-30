

import static org.junit.Assert.*;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import parmenidianEnumerations.Metric_Enums;
import model.constructs.DiachronicGraph;
import model.constructs.DiachronicGraphFactory;
import model.constructs.IDiachronicGraph;
import model.graphMetrics.DiachronicGraphMetrics;

import model.metricsReport.MetricsReportEngine;
import model.metricsReport.ReportFactory;
import model.metricsReport.VertexMetricsReport;


/**
 * Testing {@link model.metricsReport.ReportFactory} behavior when the instance of {@link model.constructs.DiachronicGraph} is null.
 * @author MZ-IK
 * @version 1.0
 * @since 2017-05-23 (Upd. by KD on 2018-10-29)
 *
 */

public class ReportFactoryTester {
	
	private ReportFactory reportFactory = new ReportFactory();
	private Metric_Enums metric1 = Metric_Enums.CLUSTERING_COEFFICIENT;
	private DiachronicGraph diachronicGraph = null;
	private MetricsReportEngine expectedResult;
	private static DiachronicGraphMetrics gMetrics;
	private static final String OUTPUT_FOLDER = "test/test_output";
	private static IDiachronicGraph dg;
	private static DiachronicGraphFactory dgFactory;
	
	@Before
	 public void before() {
		gMetrics = Mockito.mock(DiachronicGraphMetrics.class);
		
	}
	

	@Test
	public void testDGNull() {
		expectedResult = (MetricsReportEngine) reportFactory.getMetricsReportEngine(OUTPUT_FOLDER, metric1, diachronicGraph,gMetrics);
		assertNull("Diachronic Graph is null",expectedResult);
		
	}
	
	
	@Test
	public void testGetMetricsReportEngine() {
		
		dgFactory = new DiachronicGraphFactory();
		dg = dgFactory.createDiachronicGraph();
		
		expectedResult = (MetricsReportEngine) reportFactory.getMetricsReportEngine(OUTPUT_FOLDER, metric1, dg,gMetrics);		
		assertThat(expectedResult, CoreMatchers.instanceOf(VertexMetricsReport.class));		
	
	}

}