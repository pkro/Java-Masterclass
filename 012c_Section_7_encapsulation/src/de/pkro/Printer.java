package de.pkro;

public class Printer {
    private int pagesPrinted;
    private int tonerLevel;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        this.pagesPrinted = 0;
        if (tonerLevel < 0) {
            tonerLevel = -1;
        }
        this.tonerLevel = tonerLevel;
        this.duplex = duplex;
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount <= 0 || tonerAmount > 100) {
            return -1;
        }
        if (tonerAmount + tonerLevel > 100) {
            return -1;
        } else {
            tonerLevel += tonerAmount;
        }
        return tonerLevel;
    }

    public int printPages(int pages) {
        if (pages < 1) {
            return -1;
        }

        int pagesToPrint = pages;
        if (duplex) {
            pagesToPrint = (int) Math.ceil((double) pages / 2.0);
        }

        if (tonerLevel > 0) {
            pagesPrinted += pagesToPrint;
        }

        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}
