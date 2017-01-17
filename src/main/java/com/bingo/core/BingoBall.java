package com.bingo.core;

/**
 * Created by damianijr on 1/7/17.
 */
public class BingoBall {

    private final Integer number;
    private boolean raffled;

    public BingoBall(final Integer number, final boolean raffled) {
        this.number = number;
        this.raffled = raffled;
    }

    public Integer getNumber() {
        return number;
    }

    public boolean isRaffled() {
        return raffled;
    }

    public void raffled() {
        this.raffled = true;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BingoBall that = (BingoBall) o;

        if (raffled != that.raffled) return false;
        return number.equals(that.number);

    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + (raffled ? 1 : 0);
        return result;
    }

    void unraffled() {
        this.raffled = false;
    }
}
