package ggc.core;

import java.io.Serializable;
import java.util.*;

public class Recipe implements Serializable{
    /**
    * Serial number for serialization.
    */
    private static final long serialVersionUID = 202109192006L;

	private List<Component> components = new ArrayList<>(); 

    void addComponent(Component component) {
        components.add(component);
    }

	@Override
    public String toString() {
        String recipe = "";
        for (Component c : components)
            recipe += c.getId() + ":" + c.getQuantity() + "#";
        recipe = recipe.substring(0, recipe.length() - 1);
        return recipe;
    }
}
