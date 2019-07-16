package com.katsubo.finaltask.command;

import java.util.Objects;

public class CommandResult {

    private String page;
    private boolean redirect;

    public CommandResult() {
    }

    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public CommandResult(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
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
