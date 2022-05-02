package comp3111.covid.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;


/**
 * This is a class which is for GUI to cancel selection
 * 
 * <a href="https://stackoverflow.com/questions/20621752/javafx-make-listview-not-selectable-via-mouse"> Reference </a>\
 *
 * @author Oscar Tse
 *
 * @param <T> the type of selection in the list
 */
public class GUIPreventSelection<T> extends MultipleSelectionModel<T> {
	/**
	 * Force return empty string
	 * @return empty option
	 */
	@Override
    public ObservableList<Integer> getSelectedIndices() {
        return FXCollections.emptyObservableList();
    }

	/**
	 * Force return empty string
	 * @return empty option
	 */
    @Override
    public ObservableList<T> getSelectedItems() {
        return FXCollections.emptyObservableList();
    }

    /**
     * Force to do nothing
     */
    @Override
    public void selectIndices(int index, int... indices) {
    }

    /**
     * Force to do nothing
     */
    @Override
    public void selectAll() {
    }
    
    /**
     * Force to do nothing
     */
    @Override
    public void selectFirst() {
    }
    
    /**
     * Force to do nothing
     */
    @Override
    public void selectLast() {
    }

    /**
     * Force to return nothing
     */
    @Override
    public void clearAndSelect(int index) {
    }
    
    /**
     * Force to set nothing
     */
    @Override
    public void select(int index) {
    }
    
    /**
     * Force to set nothing
     */
    @Override
    public void select(T obj) {
    }
    
    /**
     * Force to set nothing
     */
    @Override
    public void clearSelection(int index) {
    }
    
    /**
     * Force to set nothing
     */
    @Override
    public void clearSelection() {
    }
    /**
     * Must return false
     */
    @Override
    public boolean isSelected(int index) {
        return false;
    }
    
    /**
     * Force to return true
     */
    @Override
    public boolean isEmpty() {
        return true;
    }
    /**
     * Force to set nothing
     */
    @Override
    public void selectPrevious() {
    }
    /**
     * Force to set nothing
     */
    @Override
    public void selectNext() {
    }
}
