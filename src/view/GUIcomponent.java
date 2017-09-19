package view;

/**
 * Represents a GUI component that can be updated on a tick, whether that is an 
 * update method call to the GUI object or rendering on a timely basis (e.g. 60fps)
 * @author J Woods
 *
 */
public interface GUIcomponent {
	public void update();
}
