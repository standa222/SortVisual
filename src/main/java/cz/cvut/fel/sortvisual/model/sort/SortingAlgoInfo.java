package cz.cvut.fel.sortvisual.model.sort;

public class SortingAlgoInfo {
    private String name;
    private String avgTimeComplexity;
    private String worstTimeComplexity;
    private String worstTimeComplexityNote = null;
    private String bestTimeComplexity;
    private String bestTimeComplexityNote = null;

    public String getName() {
        return name;
    }

    public String getAvgTimeComplexity() {
        return avgTimeComplexity;
    }

    public String getWorstTimeComplexity() {
        return worstTimeComplexity;
    }

    public String getWorstTimeComplexityNote() {
        return worstTimeComplexityNote;
    }

    public String getBestTimeComplexity() {
        return bestTimeComplexity;
    }

    public String getBestTimeComplexityNote() {
        return bestTimeComplexityNote;
    }

    public SortingAlgoInfo(String name, String avgTimeComplexity, String worstTimeComplexity, String worstTimeComplexityNote, String bestTimeComplexity, String bestTimeComplexityNote) {
        this.name = name;
        this.avgTimeComplexity = avgTimeComplexity;
        this.worstTimeComplexity = worstTimeComplexity;
        this.worstTimeComplexityNote = worstTimeComplexityNote;
        this.bestTimeComplexity = bestTimeComplexity;
        this.bestTimeComplexityNote = bestTimeComplexityNote;
    }
}
