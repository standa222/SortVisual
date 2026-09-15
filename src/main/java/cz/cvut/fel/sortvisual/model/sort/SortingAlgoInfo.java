package cz.cvut.fel.sortvisual.model.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SortingAlgoInfo {
    private String name;
    private String avgTimeComplexity;
    private boolean stable;
    private String worstTimeComplexity;
    private String worstTimeComplexityNote;
    private String bestTimeComplexity;
    private String bestTimeComplexityNote;
    private String spaceComplexity;
}
