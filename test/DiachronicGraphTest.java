import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import dataImport.GraphmlLoaderFactory;
import dataImport.IGraphmlLoader;
import model.constructs.DiachronicGraphFactory;
import model.constructs.IDiachronicGraph;


/**
 * 
 * Testing {@link model.constructs.DiachronicGraph} class using "Egee" as dataset.
 * @author MZ - IK
 * @version 2.0 
 * @since 2018-03-04 (Upd. by KD on 2018-10-29)
 *
 */

public class DiachronicGraphTest {
	
	private static IDiachronicGraph dg;
	private static DiachronicGraphFactory dgFactory;
	private static IGraphmlLoader gmlLoader;
	private static GraphmlLoaderFactory gmlFactory;
	
	private static final String INPUT_FOLDER = "test/test_input";

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		dgFactory = new DiachronicGraphFactory();
		dg = dgFactory.createDiachronicGraph();
		gmlFactory = new GraphmlLoaderFactory();
		gmlLoader = gmlFactory.createGraphmlLoader(INPUT_FOLDER.concat("/layout.graphml"));
		//dg.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges(), INPUT_FOLDER.concat("/processed schemata"), OUTPUT_FOLDER,0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		dg.loadDiachronicGraph(gmlLoader.getNodes(), gmlLoader.getEdges());
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
	public void testDiachronicGraph() {
		assertNotNull("After setup, diachronic graph is not null", dg);
	}

	@Test
	public void testGetNodes() {
		assertNotNull("Nodes are not null", dg.getNodes());
	}

	@Test
	public void testGetEdges() {
		assertNotNull("Edges are not null", dg.getEdges());
	}


	@Ignore
	public void testGetTargetFolder() {
		assertNotNull("target folder not null", dg.getTargetFolder());
	}


	@Test
	public void testGetVersions() {
		assertNotNull("versions not null", dg.getVersions());
	}


}
