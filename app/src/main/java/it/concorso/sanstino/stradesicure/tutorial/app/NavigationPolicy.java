package it.concorso.sanstino.stradesicure.tutorial.app;

/**
 * Created by Root on 18/04/2016.
 */
public interface NavigationPolicy {

    /**
     * Return true if going forwards is allowed.
     */
    boolean canGoForward(int position);

    /**
     * Return true if going backwards is allowed.
     */
    boolean canGoBackward(int position);
}