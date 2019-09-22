package com.katsubo.finaltask.entity;

import java.util.EnumSet;
import java.util.Objects;

/**
 * The type Permission.
 */
public class Permission extends Entity {
    private String name;
    private EnumSet<Rule> rules;

    public Permission() {
        rules = EnumSet.noneOf(Rule.class);

    }

    /**
     * Instantiates a new Permission.
     *
     * @param name the name
     */

    public Permission(String name) {
        this();
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Add rule.
     *
     * @param rule the rule
     */
    public void addRule(Rule rule) {
        rules.add(rule);
    }

    /**
     * Gets rules.
     *
     * @return the rules
     */
    public EnumSet<Rule> getRules() {
        return rules;
    }

    /**
     * Check rule boolean.
     *
     * @param rule the rule
     * @return the boolean
     */
    public Boolean checkRule(Rule rule){
        return rules.contains(rule);
    }

    /**
     * Sets rules.
     *
     * @param rules the rules
     */
    public void setRules(EnumSet<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Permission that = (Permission) o;
        return Objects.equals(rules, that.rules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, rules);
    }

    @Override
    public String toString() {
        return "Permission{" +
                "name='" + name + '\'' +
                ", rules=" + rules +
                '}';
    }
}
