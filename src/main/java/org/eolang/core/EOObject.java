package org.eolang.core;

import org.eolang.core.data.EOData;
import org.eolang.core.data.EODataObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * Basic EO object. Based on this class, classes are created for creating user objects.
 */
public abstract class EOObject implements Cloneable {
    /**
     * Link to the parent of the object
     */
    protected EOObject _parent;

    /**
     * Checking if an object can be datarized when creating a datarized object for caching.
     * A variant of solving the problem of exponential growth of datarization time during recursion.
     *
     * @return the boolean
     */
    public boolean _isCalculable() {
        return false;
    }

    public EOObject _getDecoratedObject() {
        return null;
    }

    public EOObject _getParentObject() {
        return null;
    }

    /**
     * Setting the parent object.
     *
     * @param _parent The parent object
     * @return this
     */
    public EOObject _setParent(EOObject _parent) {
        if (this._parent == null)
            this._parent = _parent;
        return this;
    }

    /**
     * Function that performs dateization of the object
     *
     * @return Data
     */
    public EOData _getData() {
        EOObject decoratee = _getDecoratedObject();
        if (decoratee == null) {
            throw new RuntimeException("Object cannot be dataized.");
        }
        return decoratee._getData();
    }

    /**
     * Creation of a copy of an object.
     *
     * @return a copy of the object
     */
    public EOObject _clone() {
        return this;

    }

    /**
     * Copying an attribute of an object by name.
     *
     * @param name Object name
     * @return Object Attribute
     */
    public EOObject _getAttribute(String name){
        EOObject res = new EODataObject();
        try {
            System.out.println(Arrays.toString(this.getClass().getDeclaredMethods()));
            Method method = this.getClass().getDeclaredMethod(name);
//            System.out.println("name: " + name);
//            System.out.println("method: " + method);
            method.setAccessible(true);
            return (EOObject)method.invoke(this);
        } catch ( NoSuchMethodException e) {
//            throw new RuntimeException(String.format("Can't access the %s attribute of the %s object", name, this.getClass().getName()));
            e.printStackTrace();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Copying an attribute of an object by name with the installation of free attributes
     *
     * @param name Object name
     * @return Object Attribute
     * @param freeAtt Available attributes
     */
    public EOObject _getAttribute(String name, EOObject freeAtt) {
        EOObject res = new EODataObject();
        try {
            Method method = this.getClass().getDeclaredMethod( name, EOObject.class);
            method.setAccessible(true);
            return (EOObject) method.invoke(this, freeAtt);
        } catch ( NoSuchMethodException e) {
            throw new RuntimeException(String.format("Can't access the %s attribute of the %s object", name, this.getClass().getName()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    public EOObject _getAttribute(String name, EOObject freeAtt1, EOObject freeAtt2) {
        EOObject res = new EODataObject();
        try {
            Method method = this.getClass().getDeclaredMethod("EO" + name, EOObject.class, EOObject.class);
            method.setAccessible(true);
            return (EOObject) method.invoke(this, freeAtt1, freeAtt2);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("Can't access the %s attribute of the %s object", name, this.getClass().getName()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }

    public EOObject _getAttribute(String name, EOObject freeAtt1, EOObject freeAtt2, EOObject freeAtt3) {
        EOObject res = new EODataObject();
        try {
            Method method = this.getClass().getDeclaredMethod("EO" + name, EOObject.class, EOObject.class, EOObject.class);
            method.setAccessible(true);
            return (EOObject) method.invoke(this, freeAtt1, freeAtt2, freeAtt3);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(String.format("Can't access the %s attribute of the %s object", name, this.getClass().getName()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return res;
    }
}
