package cz.cvut.fel.sortvisual.model.sort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortingAlgoInfo {
    private String name;
    private String avgTimeComplexity;
    private String worstTimeComplexity;
    private String worstTimeComplexityNote = null;
    private String bestTimeComplexity;
    private String bestTimeComplexityNote = null;
}
