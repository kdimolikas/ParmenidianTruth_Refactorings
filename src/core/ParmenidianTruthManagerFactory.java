package core;

/**
 * Creating {@link core.IParmenidianTruth} interface.
 * @author KD
 * @since 2017
 */

public class ParmenidianTruthManagerFactory {
	
	
	public IParmenidianTruth getManager(){
		
		return new ParmenidianTruthManager();
	}
	
}