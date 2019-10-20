package com.katsubo.finaltask.command;

import java.util.Objects;

/**
 * The type Command result.
 */
public class CommandResult {

    private String page;
    private boolean redirect;

    /**
     * Instantiates a new Command result.
     */
    public CommandResult() {
    }

    /**
     * Instantiates a new Command result.
     *
     * @param page     the page
     * @param redirect the redirect
     */
    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    /**
     * Instantiates a new Command result.
     *
     * @param page the page
     */
    public CommandResult(String page) {
        this.page = page;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Is redirect boolean.
     *
     * @return the boolean
     */
    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return redirect == that.redirect &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, redirect);
    }
}
