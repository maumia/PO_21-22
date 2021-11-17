package ggc.core;

import java.util.*;

public class Recipe {

	private List<Component> components = new ArrayList<>(); 

    void addComponent(Component component) {
        components.add(component);
    }

	@Override
    public String toString() {
        String recipe = null;
        for (Component c : components)
            recipe += c.getId() + ":" + c.getQuantity() + "#";
        recipe = recipe.substring(0, recipe.length() - 1);
        return recipe;
    }
}
