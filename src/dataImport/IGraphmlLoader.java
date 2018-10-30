package dataImport;

import java.util.ArrayList;

import model.constructs.ForeignKey;
import model.constructs.Table;


/**
 * Responsible for providing tables and foreign keys of the schema.
 * @author KD
 * @since 2018-02-16
 *
 */

public interface IGraphmlLoader {
	
	public ArrayList<Table> getNodes();
	public ArrayList<ForeignKey> getEdges();

}
